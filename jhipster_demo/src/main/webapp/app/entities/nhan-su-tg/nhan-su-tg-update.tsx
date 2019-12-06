import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOfficer } from 'app/shared/model/officer.model';
import { getEntities as getOfficers } from 'app/entities/officer/officer.reducer';
import { IDeTai } from 'app/shared/model/de-tai.model';
import { getEntities as getDeTais } from 'app/entities/de-tai/de-tai.reducer';
import { getEntity, updateEntity, createEntity, reset } from './nhan-su-tg.reducer';
import { INhanSuTG } from 'app/shared/model/nhan-su-tg.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INhanSuTGUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INhanSuTGUpdateState {
  isNew: boolean;
  officerId: string;
  deTaiId: string;
}

export class NhanSuTGUpdate extends React.Component<INhanSuTGUpdateProps, INhanSuTGUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      officerId: '0',
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

    this.props.getOfficers();
    this.props.getDeTais();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { nhanSuTGEntity } = this.props;
      const entity = {
        ...nhanSuTGEntity,
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
    this.props.history.push('/nhan-su-tg');
  };

  render() {
    const { nhanSuTGEntity, officers, deTais, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhispterDemoApp.nhanSuTG.home.createOrEditLabel">
              <Translate contentKey="jhispterDemoApp.nhanSuTG.home.createOrEditLabel">Create or edit a NhanSuTG</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : nhanSuTGEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="nhan-su-tg-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="nhan-su-tg-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="vaiTroLabel" for="nhan-su-tg-vaiTro">
                    <Translate contentKey="jhispterDemoApp.nhanSuTG.vaiTro">Vai Tro</Translate>
                  </Label>
                  <AvField
                    id="nhan-su-tg-vaiTro"
                    type="text"
                    name="vaiTro"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="nhan-su-tg-officer">
                    <Translate contentKey="jhispterDemoApp.nhanSuTG.officer">Officer</Translate>
                  </Label>
                  <AvInput id="nhan-su-tg-officer" type="select" className="form-control" name="officer.id">
                    <option value="" key="0" />
                    {officers
                      ? officers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="nhan-su-tg-deTai">
                    <Translate contentKey="jhispterDemoApp.nhanSuTG.deTai">De Tai</Translate>
                  </Label>
                  <AvInput id="nhan-su-tg-deTai" type="select" className="form-control" name="deTai.id">
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
                <Button tag={Link} id="cancel-save" to="/nhan-su-tg" replace color="info">
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
  officers: storeState.officer.entities,
  deTais: storeState.deTai.entities,
  nhanSuTGEntity: storeState.nhanSuTG.entity,
  loading: storeState.nhanSuTG.loading,
  updating: storeState.nhanSuTG.updating,
  updateSuccess: storeState.nhanSuTG.updateSuccess
});

const mapDispatchToProps = {
  getOfficers,
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
)(NhanSuTGUpdate);
