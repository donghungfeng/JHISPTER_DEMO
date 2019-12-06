import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './file-attach.reducer';
import { IFileAttach } from 'app/shared/model/file-attach.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFileAttachDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FileAttachDetail extends React.Component<IFileAttachDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { fileAttachEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.fileAttach.detail.title">FileAttach</Translate> [<b>{fileAttachEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="moTa">
                <Translate contentKey="jhispterDemoApp.fileAttach.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{fileAttachEntity.moTa}</dd>
            <dt>
              <span id="noiDung">
                <Translate contentKey="jhispterDemoApp.fileAttach.noiDung">Noi Dung</Translate>
              </span>
            </dt>
            <dd>
              {fileAttachEntity.noiDung ? (
                <div>
                  <a onClick={openFile(fileAttachEntity.noiDungContentType, fileAttachEntity.noiDung)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {fileAttachEntity.noiDungContentType}, {byteSize(fileAttachEntity.noiDung)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="ngayCapNhat">
                <Translate contentKey="jhispterDemoApp.fileAttach.ngayCapNhat">Ngay Cap Nhat</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={fileAttachEntity.ngayCapNhat} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.fileAttach.deTai">De Tai</Translate>
            </dt>
            <dd>{fileAttachEntity.deTai ? fileAttachEntity.deTai.id : ''}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.fileAttach.tienDo">Tien Do</Translate>
            </dt>
            <dd>{fileAttachEntity.tienDo ? fileAttachEntity.tienDo.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/file-attach" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/file-attach/${fileAttachEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ fileAttach }: IRootState) => ({
  fileAttachEntity: fileAttach.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FileAttachDetail);
