import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './de-tai.reducer';
import { IDeTai } from 'app/shared/model/de-tai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDeTaiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class DeTai extends React.Component<IDeTaiProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { deTaiList, match } = this.props;
    return (
      <div>
        <h2 id="de-tai-heading">
          <Translate contentKey="jhispterDemoApp.deTai.home.title">De Tais</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.deTai.home.createLabel">Create a new De Tai</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {deTaiList && deTaiList.length > 0 ? (
            <Table responsive aria-describedby="de-tai-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.maDeTai">Ma De Tai</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.tenDeTai">Ten De Tai</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.mucTieu">Muc Tieu</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.ngayBDDuKien">Ngay BD Du Kien</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.ngayKTDuKien">Ngay KT Du Kien</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.kinhPhiDuKien">Kinh Phi Du Kien</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.noiDungTongQuan">Noi Dung Tong Quan</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.kinhPhiHoanThanh">Kinh Phi Hoan Thanh</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.tongDiem">Tong Diem</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.officer">Officer</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.trangThai">Trang Thai</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.capDeTai">Cap De Tai</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.deTai.xepLoai">Xep Loai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {deTaiList.map((deTai, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${deTai.id}`} color="link" size="sm">
                        {deTai.id}
                      </Button>
                    </td>
                    <td>{deTai.maDeTai}</td>
                    <td>{deTai.tenDeTai}</td>
                    <td>{deTai.mucTieu}</td>
                    <td>
                      <TextFormat type="date" value={deTai.ngayBDDuKien} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={deTai.ngayKTDuKien} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{deTai.kinhPhiDuKien}</td>
                    <td>{deTai.noiDungTongQuan}</td>
                    <td>{deTai.kinhPhiHoanThanh}</td>
                    <td>{deTai.tongDiem}</td>
                    <td>{deTai.officer ? <Link to={`officer/${deTai.officer.id}`}>{deTai.officer.id}</Link> : ''}</td>
                    <td>{deTai.trangThai ? <Link to={`trang-thai/${deTai.trangThai.id}`}>{deTai.trangThai.id}</Link> : ''}</td>
                    <td>{deTai.capDeTai ? <Link to={`cap-de-tai/${deTai.capDeTai.id}`}>{deTai.capDeTai.id}</Link> : ''}</td>
                    <td>{deTai.xepLoai ? <Link to={`xep-loai/${deTai.xepLoai.id}`}>{deTai.xepLoai.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${deTai.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${deTai.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${deTai.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.deTai.home.notFound">No De Tais found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ deTai }: IRootState) => ({
  deTaiList: deTai.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DeTai);
