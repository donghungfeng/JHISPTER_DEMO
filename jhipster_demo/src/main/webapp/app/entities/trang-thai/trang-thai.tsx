import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './trang-thai.reducer';
import { ITrangThai } from 'app/shared/model/trang-thai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrangThaiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TrangThai extends React.Component<ITrangThaiProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { trangThaiList, match } = this.props;
    return (
      <div>
        <h2 id="trang-thai-heading">
          <Translate contentKey="jhispterDemoApp.trangThai.home.title">Trang Thais</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.trangThai.home.createLabel">Create a new Trang Thai</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {trangThaiList && trangThaiList.length > 0 ? (
            <Table responsive aria-describedby="trang-thai-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.trangThai.tenTrangThai">Ten Trang Thai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {trangThaiList.map((trangThai, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${trangThai.id}`} color="link" size="sm">
                        {trangThai.id}
                      </Button>
                    </td>
                    <td>{trangThai.tenTrangThai}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${trangThai.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${trangThai.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${trangThai.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.trangThai.home.notFound">No Trang Thais found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ trangThai }: IRootState) => ({
  trangThaiList: trangThai.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TrangThai);
