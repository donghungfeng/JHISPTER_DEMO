import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IToChuc } from 'app/shared/model/to-chuc.model';
import { getEntities as getToChucs } from 'app/entities/to-chuc/to-chuc.reducer';
import { getEntity, updateEntity, createEntity, reset } from './officer.reducer';
import { IOfficer } from 'app/shared/model/officer.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOfficerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IOfficerUpdateState {
  isNew: boolean;
  toChucId: string;
}

export class OfficerUpdate extends React.Component<IOfficerUpdateProps, IOfficerUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      toChucId: '0',
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

    this.props.getToChucs();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { officerEntity } = this.props;
      const entity = {
        ...officerEntity,
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
    this.props.history.push('/officer');
  };

  render() {
    const { officerEntity, toChucs, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhispterDemoApp.officer.home.createOrEditLabel">
              <Translate contentKey="jhispterDemoApp.officer.home.createOrEditLabel">Create or edit a Officer</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : officerEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="officer-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="officer-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tenNhanSuLabel" for="officer-tenNhanSu">
                    <Translate contentKey="jhispterDemoApp.officer.tenNhanSu">Ten Nhan Su</Translate>
                  </Label>
                  <AvField
                    id="officer-tenNhanSu"
                    type="text"
                    name="tenNhanSu"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dienThoaiLabel" for="officer-dienThoai">
                    <Translate contentKey="jhispterDemoApp.officer.dienThoai">Dien Thoai</Translate>
                  </Label>
                  <AvField
                    id="officer-dienThoai"
                    type="text"
                    name="dienThoai"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="officer-email">
                    <Translate contentKey="jhispterDemoApp.officer.email">Email</Translate>
                  </Label>
                  <AvField
                    id="officer-email"
                    type="text"
                    name="email"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="faxLabel" for="officer-fax">
                    <Translate contentKey="jhispterDemoApp.officer.fax">Fax</Translate>
                  </Label>
                  <AvField id="officer-fax" type="text" name="fax" />
                </AvGroup>
                <AvGroup>
                  <Label id="diaChiLabel" for="officer-diaChi">
                    <Translate contentKey="jhispterDemoApp.officer.diaChi">Dia Chi</Translate>
                  </Label>
                  <AvField
                    id="officer-diaChi"
                    type="text"
                    name="diaChi"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="officer-toChuc">
                    <Translate contentKey="jhispterDemoApp.officer.toChuc">To Chuc</Translate>
                  </Label>
                  <AvInput id="officer-toChuc" type="select" className="form-control" name="toChuc.id">
                    <option value="" key="0" />
                    {toChucs
                      ? toChucs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/officer" replace color="info">
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
  toChucs: storeState.toChuc.entities,
  officerEntity: storeState.officer.entity,
  loading: storeState.officer.loading,
  updating: storeState.officer.updating,
  updateSuccess: storeState.officer.updateSuccess
});

const mapDispatchToProps = {
  getToChucs,
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
)(OfficerUpdate);
