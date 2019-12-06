import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './cap-de-tai.reducer';
import { ICapDeTai } from 'app/shared/model/cap-de-tai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICapDeTaiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CapDeTai extends React.Component<ICapDeTaiProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { capDeTaiList, match } = this.props;
    return (
      <div>
        <h2 id="cap-de-tai-heading">
          <Translate contentKey="jhispterDemoApp.capDeTai.home.title">Cap De Tais</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.capDeTai.home.createLabel">Create a new Cap De Tai</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {capDeTaiList && capDeTaiList.length > 0 ? (
            <Table responsive aria-describedby="cap-de-tai-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.capDeTai.tenCapDeTai">Ten Cap De Tai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {capDeTaiList.map((capDeTai, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${capDeTai.id}`} color="link" size="sm">
                        {capDeTai.id}
                      </Button>
                    </td>
                    <td>{capDeTai.tenCapDeTai}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${capDeTai.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${capDeTai.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${capDeTai.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.capDeTai.home.notFound">No Cap De Tais found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ capDeTai }: IRootState) => ({
  capDeTaiList: capDeTai.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CapDeTai);
