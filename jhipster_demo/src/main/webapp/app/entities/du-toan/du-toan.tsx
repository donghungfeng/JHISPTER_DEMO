import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './du-toan.reducer';
import { IDuToan } from 'app/shared/model/du-toan.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDuToanProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class DuToan extends React.Component<IDuToanProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { duToanList, match } = this.props;
    return (
      <div>
        <h2 id="du-toan-heading">
          <Translate contentKey="jhispterDemoApp.duToan.home.title">Du Toans</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.duToan.home.createLabel">Create a new Du Toan</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {duToanList && duToanList.length > 0 ? (
            <Table responsive aria-describedby="du-toan-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.duToan.loaiDuToan">Loai Du Toan</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.duToan.duToan">Du Toan</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.duToan.deTai">De Tai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {duToanList.map((duToan, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${duToan.id}`} color="link" size="sm">
                        {duToan.id}
                      </Button>
                    </td>
                    <td>{duToan.loaiDuToan}</td>
                    <td>{duToan.duToan}</td>
                    <td>{duToan.deTai ? <Link to={`de-tai/${duToan.deTai.id}`}>{duToan.deTai.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${duToan.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${duToan.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${duToan.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.duToan.home.notFound">No Du Toans found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ duToan }: IRootState) => ({
  duToanList: duToan.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DuToan);
