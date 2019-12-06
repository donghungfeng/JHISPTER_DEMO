import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDeTai } from 'app/shared/model/de-tai.model';
import { getEntities as getDeTais } from 'app/entities/de-tai/de-tai.reducer';
import { ITienDo } from 'app/shared/model/tien-do.model';
import { getEntities as getTienDos } from 'app/entities/tien-do/tien-do.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './file-attach.reducer';
import { IFileAttach } from 'app/shared/model/file-attach.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFileAttachUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFileAttachUpdateState {
  isNew: boolean;
  deTaiId: string;
  tienDoId: string;
}

export class FileAttachUpdate extends React.Component<IFileAttachUpdateProps, IFileAttachUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      deTaiId: '0',
      tienDoId: '0',
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
    this.props.getTienDos();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { fileAttachEntity } = this.props;
      const entity = {
        ...fileAttachEntity,
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
    this.props.history.push('/file-attach');
  };

  render() {
    const { fileAttachEntity, deTais, tienDos, loading, updating } = this.props;
    const { isNew } = this.state;

    const { noiDung, noiDungContentType } = fileAttachEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhispterDemoApp.fileAttach.home.createOrEditLabel">
              <Translate contentKey="jhispterDemoApp.fileAttach.home.createOrEditLabel">Create or edit a FileAttach</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : fileAttachEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="file-attach-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="file-attach-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="moTaLabel" for="file-attach-moTa">
                    <Translate contentKey="jhispterDemoApp.fileAttach.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField
                    id="file-attach-moTa"
                    type="text"
                    name="moTa"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="noiDungLabel" for="noiDung">
                      <Translate contentKey="jhispterDemoApp.fileAttach.noiDung">Noi Dung</Translate>
                    </Label>
                    <br />
                    {noiDung ? (
                      <div>
                        <a onClick={openFile(noiDungContentType, noiDung)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {noiDungContentType}, {byteSize(noiDung)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('noiDung')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_noiDung" type="file" onChange={this.onBlobChange(false, 'noiDung')} />
                    <AvInput
                      type="hidden"
                      name="noiDung"
                      value={noiDung}
                      validate={{
                        required: { value: true, errorMessage: translate('entity.validation.required') }
                      }}
                    />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="ngayCapNhatLabel" for="file-attach-ngayCapNhat">
                    <Translate contentKey="jhispterDemoApp.fileAttach.ngayCapNhat">Ngay Cap Nhat</Translate>
                  </Label>
                  <AvField
                    id="file-attach-ngayCapNhat"
                    type="date"
                    className="form-control"
                    name="ngayCapNhat"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="file-attach-deTai">
                    <Translate contentKey="jhispterDemoApp.fileAttach.deTai">De Tai</Translate>
                  </Label>
                  <AvInput id="file-attach-deTai" type="select" className="form-control" name="deTai.id">
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
                <AvGroup>
                  <Label for="file-attach-tienDo">
                    <Translate contentKey="jhispterDemoApp.fileAttach.tienDo">Tien Do</Translate>
                  </Label>
                  <AvInput id="file-attach-tienDo" type="select" className="form-control" name="tienDo.id">
                    <option value="" key="0" />
                    {tienDos
                      ? tienDos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/file-attach" replace color="info">
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
  tienDos: storeState.tienDo.entities,
  fileAttachEntity: storeState.fileAttach.entity,
  loading: storeState.fileAttach.loading,
  updating: storeState.fileAttach.updating,
  updateSuccess: storeState.fileAttach.updateSuccess
});

const mapDispatchToProps = {
  getDeTais,
  getTienDos,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FileAttachUpdate);
