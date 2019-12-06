import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './officer.reducer';
import { IOfficer } from 'app/shared/model/officer.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfficerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Officer extends React.Component<IOfficerProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { officerList, match } = this.props;
    return (
      <div>
        <h2 id="officer-heading">
          <Translate contentKey="jhispterDemoApp.officer.home.title">Officers</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.officer.home.createLabel">Create a new Officer</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {officerList && officerList.length > 0 ? (
            <Table responsive aria-describedby="officer-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.officer.tenNhanSu">Ten Nhan Su</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.officer.dienThoai">Dien Thoai</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.officer.email">Email</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.officer.fax">Fax</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.officer.diaChi">Dia Chi</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.officer.toChuc">To Chuc</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {officerList.map((officer, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${officer.id}`} color="link" size="sm">
                        {officer.id}
                      </Button>
                    </td>
                    <td>{officer.tenNhanSu}</td>
                    <td>{officer.dienThoai}</td>
                    <td>{officer.email}</td>
                    <td>{officer.fax}</td>
                    <td>{officer.diaChi}</td>
                    <td>{officer.toChuc ? <Link to={`to-chuc/${officer.toChuc.id}`}>{officer.toChuc.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${officer.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${officer.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${officer.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.officer.home.notFound">No Officers found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ officer }: IRootState) => ({
  officerList: officer.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Officer);
