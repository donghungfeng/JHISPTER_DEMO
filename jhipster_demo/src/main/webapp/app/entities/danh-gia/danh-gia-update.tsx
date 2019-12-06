import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDeTai } from 'app/shared/model/de-tai.model';
import { getEntities as getDeTais } from 'app/entities/de-tai/de-tai.reducer';
import { getEntity, updateEntity, createEntity, reset } from './danh-gia.reducer';
import { IDanhGia } from 'app/shared/model/danh-gia.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDanhGiaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDanhGiaUpdateState {
  isNew: boolean;
  deTaiId: string;
}

export class DanhGiaUpdate extends React.Component<IDanhGiaUpdateProps, IDanhGiaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      deTaiId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getDeTais();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { danhGiaEntity } = this.props;
      const entity = {
        ...danhGiaEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/danh-gia');
  };

  render() {
    const { danhGiaEntity, deTais, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhispterDemoApp.danhGia.home.createOrEditLabel">
              <Translate contentKey="jhispterDemoApp.danhGia.home.createOrEditLabel">Create or edit a DanhGia</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : danhGiaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="danh-gia-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="danh-gia-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="loaiDanhGiaLabel" for="danh-gia-loaiDanhGia">
                    <Translate contentKey="jhispterDemoApp.danhGia.loaiDanhGia">Loai Danh Gia</Translate>
                  </Label>
                  <AvField
                    id="danh-gia-loaiDanhGia"
                    type="string"
                    className="form-control"
                    name="loaiDanhGia"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="diemDanhGiaLabel" for="danh-gia-diemDanhGia">
                    <Translate contentKey="jhispterDemoApp.danhGia.diemDanhGia">Diem Danh Gia</Translate>
                  </Label>
                  <AvField
                    id="danh-gia-diemDanhGia"
                    type="string"
                    className="form-control"
                    name="diemDanhGia"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="danh-gia-deTai">
                    <Translate contentKey="jhispterDemoApp.danhGia.deTai">De Tai</Translate>
                  </Label>
                  <AvInput id="danh-gia-deTai" type="select" className="form-control" name="deTai.id">
                    <option value="" key="0" />
                    {deTais
                      ? deTais.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/danh-gia" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  deTais: storeState.deTai.entities,
  danhGiaEntity: storeState.danhGia.entity,
  loading: storeState.danhGia.loading,
  updating: storeState.danhGia.updating,
  updateSuccess: storeState.danhGia.updateSuccess
});

const mapDispatchToProps = {
  getDeTais,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DanhGiaUpdate);
