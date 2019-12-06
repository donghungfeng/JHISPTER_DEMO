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
import { ITrangThai } from 'app/shared/model/trang-thai.model';
import { getEntities as getTrangThais } from 'app/entities/trang-thai/trang-thai.reducer';
import { ICapDeTai } from 'app/shared/model/cap-de-tai.model';
import { getEntities as getCapDeTais } from 'app/entities/cap-de-tai/cap-de-tai.reducer';
import { IXepLoai } from 'app/shared/model/xep-loai.model';
import { getEntities as getXepLoais } from 'app/entities/xep-loai/xep-loai.reducer';
import { getEntity, updateEntity, createEntity, reset } from './de-tai.reducer';
import { IDeTai } from 'app/shared/model/de-tai.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDeTaiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDeTaiUpdateState {
  isNew: boolean;
  officerId: string;
  trangThaiId: string;
  capDeTaiId: string;
  xepLoaiId: string;
}

export class DeTaiUpdate extends React.Component<IDeTaiUpdateProps, IDeTaiUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      officerId: '0',
      trangThaiId: '0',
      capDeTaiId: '0',
      xepLoaiId: '0',
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
    this.props.getTrangThais();
    this.props.getCapDeTais();
    this.props.getXepLoais();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { deTaiEntity } = this.props;
      const entity = {
        ...deTaiEntity,
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
    this.props.history.push('/de-tai');
  };

  render() {
    const { deTaiEntity, officers, trangThais, capDeTais, xepLoais, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhispterDemoApp.deTai.home.createOrEditLabel">
              <Translate contentKey="jhispterDemoApp.deTai.home.createOrEditLabel">Create or edit a DeTai</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : deTaiEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="de-tai-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="de-tai-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maDeTaiLabel" for="de-tai-maDeTai">
                    <Translate contentKey="jhispterDemoApp.deTai.maDeTai">Ma De Tai</Translate>
                  </Label>
                  <AvField
                    id="de-tai-maDeTai"
                    type="text"
                    name="maDeTai"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tenDeTaiLabel" for="de-tai-tenDeTai">
                    <Translate contentKey="jhispterDemoApp.deTai.tenDeTai">Ten De Tai</Translate>
                  </Label>
                  <AvField
                    id="de-tai-tenDeTai"
                    type="text"
                    name="tenDeTai"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="mucTieuLabel" for="de-tai-mucTieu">
                    <Translate contentKey="jhispterDemoApp.deTai.mucTieu">Muc Tieu</Translate>
                  </Label>
                  <AvField
                    id="de-tai-mucTieu"
                    type="text"
                    name="mucTieu"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ngayBDDuKienLabel" for="de-tai-ngayBDDuKien">
                    <Translate contentKey="jhispterDemoApp.deTai.ngayBDDuKien">Ngay BD Du Kien</Translate>
                  </Label>
                  <AvField
                    id="de-tai-ngayBDDuKien"
                    type="date"
                    className="form-control"
                    name="ngayBDDuKien"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ngayKTDuKienLabel" for="de-tai-ngayKTDuKien">
                    <Translate contentKey="jhispterDemoApp.deTai.ngayKTDuKien">Ngay KT Du Kien</Translate>
                  </Label>
                  <AvField
                    id="de-tai-ngayKTDuKien"
                    type="date"
                    className="form-control"
                    name="ngayKTDuKien"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kinhPhiDuKienLabel" for="de-tai-kinhPhiDuKien">
                    <Translate contentKey="jhispterDemoApp.deTai.kinhPhiDuKien">Kinh Phi Du Kien</Translate>
                  </Label>
                  <AvField
                    id="de-tai-kinhPhiDuKien"
                    type="string"
                    className="form-control"
                    name="kinhPhiDuKien"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="noiDungTongQuanLabel" for="de-tai-noiDungTongQuan">
                    <Translate contentKey="jhispterDemoApp.deTai.noiDungTongQuan">Noi Dung Tong Quan</Translate>
                  </Label>
                  <AvField
                    id="de-tai-noiDungTongQuan"
                    type="text"
                    name="noiDungTongQuan"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kinhPhiHoanThanhLabel" for="de-tai-kinhPhiHoanThanh">
                    <Translate contentKey="jhispterDemoApp.deTai.kinhPhiHoanThanh">Kinh Phi Hoan Thanh</Translate>
                  </Label>
                  <AvField id="de-tai-kinhPhiHoanThanh" type="string" className="form-control" name="kinhPhiHoanThanh" />
                </AvGroup>
                <AvGroup>
                  <Label id="tongDiemLabel" for="de-tai-tongDiem">
                    <Translate contentKey="jhispterDemoApp.deTai.tongDiem">Tong Diem</Translate>
                  </Label>
                  <AvField id="de-tai-tongDiem" type="string" className="form-control" name="tongDiem" />
                </AvGroup>
                <AvGroup>
                  <Label for="de-tai-officer">
                    <Translate contentKey="jhispterDemoApp.deTai.officer">Officer</Translate>
                  </Label>
                  <AvInput id="de-tai-officer" type="select" className="form-control" name="officer.id">
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
                  <Label for="de-tai-trangThai">
                    <Translate contentKey="jhispterDemoApp.deTai.trangThai">Trang Thai</Translate>
                  </Label>
                  <AvInput id="de-tai-trangThai" type="select" className="form-control" name="trangThai.id">
                    <option value="" key="0" />
                    {trangThais
                      ? trangThais.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="de-tai-capDeTai">
                    <Translate contentKey="jhispterDemoApp.deTai.capDeTai">Cap De Tai</Translate>
                  </Label>
                  <AvInput id="de-tai-capDeTai" type="select" className="form-control" name="capDeTai.id">
                    <option value="" key="0" />
                    {capDeTais
                      ? capDeTais.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="de-tai-xepLoai">
                    <Translate contentKey="jhispterDemoApp.deTai.xepLoai">Xep Loai</Translate>
                  </Label>
                  <AvInput id="de-tai-xepLoai" type="select" className="form-control" name="xepLoai.id">
                    <option value="" key="0" />
                    {xepLoais
                      ? xepLoais.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/de-tai" replace color="info">
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
  trangThais: storeState.trangThai.entities,
  capDeTais: storeState.capDeTai.entities,
  xepLoais: storeState.xepLoai.entities,
  deTaiEntity: storeState.deTai.entity,
  loading: storeState.deTai.loading,
  updating: storeState.deTai.updating,
  updateSuccess: storeState.deTai.updateSuccess
});

const mapDispatchToProps = {
  getOfficers,
  getTrangThais,
  getCapDeTais,
  getXepLoais,
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
)(DeTaiUpdate);
