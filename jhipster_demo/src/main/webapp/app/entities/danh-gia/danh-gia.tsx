import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './danh-gia.reducer';
import { IDanhGia } from 'app/shared/model/danh-gia.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDanhGiaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class DanhGia extends React.Component<IDanhGiaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { danhGiaList, match } = this.props;
    return (
      <div>
        <h2 id="danh-gia-heading">
          <Translate contentKey="jhispterDemoApp.danhGia.home.title">Danh Gias</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.danhGia.home.createLabel">Create a new Danh Gia</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {danhGiaList && danhGiaList.length > 0 ? (
            <Table responsive aria-describedby="danh-gia-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.danhGia.loaiDanhGia">Loai Danh Gia</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.danhGia.diemDanhGia">Diem Danh Gia</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.danhGia.deTai">De Tai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {danhGiaList.map((danhGia, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${danhGia.id}`} color="link" size="sm">
                        {danhGia.id}
                      </Button>
                    </td>
                    <td>{danhGia.loaiDanhGia}</td>
                    <td>{danhGia.diemDanhGia}</td>
                    <td>{danhGia.deTai ? <Link to={`de-tai/${danhGia.deTai.id}`}>{danhGia.deTai.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${danhGia.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${danhGia.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${danhGia.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.danhGia.home.notFound">No Danh Gias found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ danhGia }: IRootState) => ({
  danhGiaList: danhGia.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DanhGia);
