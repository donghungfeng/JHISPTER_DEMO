import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './file-attach.reducer';
import { IFileAttach } from 'app/shared/model/file-attach.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFileAttachProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FileAttach extends React.Component<IFileAttachProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { fileAttachList, match } = this.props;
    return (
      <div>
        <h2 id="file-attach-heading">
          <Translate contentKey="jhispterDemoApp.fileAttach.home.title">File Attaches</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.fileAttach.home.createLabel">Create a new File Attach</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {fileAttachList && fileAttachList.length > 0 ? (
            <Table responsive aria-describedby="file-attach-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.fileAttach.moTa">Mo Ta</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.fileAttach.noiDung">Noi Dung</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.fileAttach.ngayCapNhat">Ngay Cap Nhat</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.fileAttach.deTai">De Tai</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.fileAttach.tienDo">Tien Do</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {fileAttachList.map((fileAttach, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${fileAttach.id}`} color="link" size="sm">
                        {fileAttach.id}
                      </Button>
                    </td>
                    <td>{fileAttach.moTa}</td>
                    <td>
                      {fileAttach.noiDung ? (
                        <div>
                          <a onClick={openFile(fileAttach.noiDungContentType, fileAttach.noiDung)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                          <span>
                            {fileAttach.noiDungContentType}, {byteSize(fileAttach.noiDung)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>
                      <TextFormat type="date" value={fileAttach.ngayCapNhat} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{fileAttach.deTai ? <Link to={`de-tai/${fileAttach.deTai.id}`}>{fileAttach.deTai.id}</Link> : ''}</td>
                    <td>{fileAttach.tienDo ? <Link to={`tien-do/${fileAttach.tienDo.id}`}>{fileAttach.tienDo.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${fileAttach.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${fileAttach.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${fileAttach.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="jhispterDemoApp.fileAttach.home.notFound">No File Attaches found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ fileAttach }: IRootState) => ({
  fileAttachList: fileAttach.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FileAttach);
