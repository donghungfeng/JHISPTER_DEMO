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
import { getEntity, updateEntity, createEntity, reset } from './du-toan.reducer';
import { IDuToan } from 'app/shared/model/du-toan.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDuToanUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDuToanUpdateState {
  isNew: boolean;
  deTaiId: string;
}

export class DuToanUpdate extends React.Component<IDuToanUpdateProps, IDuToanUpdateState> {
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
      const { duToanEntity } = this.props;
      const entity = {
        ...duToanEntity,
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
    this.props.history.push('/du-toan');
  };

  render() {
    const { duToanEntity, deTais, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhispterDemoApp.duToan.home.createOrEditLabel">
              <Translate contentKey="jhispterDemoApp.duToan.home.createOrEditLabel">Create or edit a DuToan</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : duToanEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="du-toan-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="du-toan-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="loaiDuToanLabel" for="du-toan-loaiDuToan">
                    <Translate contentKey="jhispterDemoApp.duToan.loaiDuToan">Loai Du Toan</Translate>
                  </Label>
                  <AvField
                    id="du-toan-loaiDuToan"
                    type="string"
                    className="form-control"
                    name="loaiDuToan"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="duToanLabel" for="du-toan-duToan">
                    <Translate contentKey="jhispterDemoApp.duToan.duToan">Du Toan</Translate>
                  </Label>
                  <AvField
                    id="du-toan-duToan"
                    type="string"
                    className="form-control"
                    name="duToan"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="du-toan-deTai">
                    <Translate contentKey="jhispterDemoApp.duToan.deTai">De Tai</Translate>
                  </Label>
                  <AvInput id="du-toan-deTai" type="select" className="form-control" name="deTai.id">
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
                <Button tag={Link} id="cancel-save" to="/du-toan" replace color="info">
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
  duToanEntity: storeState.duToan.entity,
  loading: storeState.duToan.loading,
  updating: storeState.duToan.updating,
  updateSuccess: storeState.duToan.updateSuccess
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
)(DuToanUpdate);
