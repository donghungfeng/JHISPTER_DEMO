import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tien-do.reducer';
import { ITienDo } from 'app/shared/model/tien-do.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITienDoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TienDo extends React.Component<ITienDoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { tienDoList, match } = this.props;
    return (
      <div>
        <h2 id="tien-do-heading">
          <Translate contentKey="jhispterDemoApp.tienDo.home.title">Tien Dos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.tienDo.home.createLabel">Create a new Tien Do</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {tienDoList && tienDoList.length > 0 ? (
            <Table responsive aria-describedby="tien-do-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.tienDo.tienDoHoanThanh">Tien Do Hoan Thanh</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.tienDo.moTa">Mo Ta</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.tienDo.ngayCapNhat">Ngay Cap Nhat</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.tienDo.deTai">De Tai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {tienDoList.map((tienDo, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${tienDo.id}`} color="link" size="sm">
                        {tienDo.id}
                      </Button>
                    </td>
                    <td>{tienDo.tienDoHoanThanh}</td>
                    <td>{tienDo.moTa}</td>
                    <td>
                      <TextFormat type="date" value={tienDo.ngayCapNhat} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{tienDo.deTai ? <Link to={`de-tai/${tienDo.deTai.id}`}>{tienDo.deTai.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${tienDo.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${tienDo.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${tienDo.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.tienDo.home.notFound">No Tien Dos found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ tienDo }: IRootState) => ({
  tienDoList: tienDo.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TienDo);
