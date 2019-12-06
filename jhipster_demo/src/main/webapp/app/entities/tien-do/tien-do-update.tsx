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
import { getEntity, updateEntity, createEntity, reset } from './tien-do.reducer';
import { ITienDo } from 'app/shared/model/tien-do.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITienDoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITienDoUpdateState {
  isNew: boolean;
  deTaiId: string;
}

export class TienDoUpdate extends React.Component<ITienDoUpdateProps, ITienDoUpdateState> {
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
      const { tienDoEntity } = this.props;
      const entity = {
        ...tienDoEntity,
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
    this.props.history.push('/tien-do');
  };

  render() {
    const { tienDoEntity, deTais, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhispterDemoApp.tienDo.home.createOrEditLabel">
              <Translate contentKey="jhispterDemoApp.tienDo.home.createOrEditLabel">Create or edit a TienDo</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tienDoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="tien-do-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="tien-do-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tienDoHoanThanhLabel" for="tien-do-tienDoHoanThanh">
                    <Translate contentKey="jhispterDemoApp.tienDo.tienDoHoanThanh">Tien Do Hoan Thanh</Translate>
                  </Label>
                  <AvField
                    id="tien-do-tienDoHoanThanh"
                    type="string"
                    className="form-control"
                    name="tienDoHoanThanh"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="tien-do-moTa">
                    <Translate contentKey="jhispterDemoApp.tienDo.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField
                    id="tien-do-moTa"
                    type="text"
                    name="moTa"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ngayCapNhatLabel" for="tien-do-ngayCapNhat">
                    <Translate contentKey="jhispterDemoApp.tienDo.ngayCapNhat">Ngay Cap Nhat</Translate>
                  </Label>
                  <AvField
                    id="tien-do-ngayCapNhat"
                    type="date"
                    className="form-control"
                    name="ngayCapNhat"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="tien-do-deTai">
                    <Translate contentKey="jhispterDemoApp.tienDo.deTai">De Tai</Translate>
                  </Label>
                  <AvInput id="tien-do-deTai" type="select" className="form-control" name="deTai.id">
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
                <Button tag={Link} id="cancel-save" to="/tien-do" replace color="info">
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
  tienDoEntity: storeState.tienDo.entity,
  loading: storeState.tienDo.loading,
  updating: storeState.tienDo.updating,
  updateSuccess: storeState.tienDo.updateSuccess
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
)(TienDoUpdate);
