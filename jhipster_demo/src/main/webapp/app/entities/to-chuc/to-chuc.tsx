import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './to-chuc.reducer';
import { IToChuc } from 'app/shared/model/to-chuc.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IToChucProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ToChuc extends React.Component<IToChucProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { toChucList, match } = this.props;
    return (
      <div>
        <h2 id="to-chuc-heading">
          <Translate contentKey="jhispterDemoApp.toChuc.home.title">To Chucs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.toChuc.home.createLabel">Create a new To Chuc</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {toChucList && toChucList.length > 0 ? (
            <Table responsive aria-describedby="to-chuc-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.toChuc.tenToChuc">Ten To Chuc</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {toChucList.map((toChuc, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${toChuc.id}`} color="link" size="sm">
                        {toChuc.id}
                      </Button>
                    </td>
                    <td>{toChuc.tenToChuc}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${toChuc.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${toChuc.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${toChuc.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.toChuc.home.notFound">No To Chucs found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ toChuc }: IRootState) => ({
  toChucList: toChuc.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ToChuc);
