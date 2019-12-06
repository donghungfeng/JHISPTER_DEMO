import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nhan-su-tg.reducer';
import { INhanSuTG } from 'app/shared/model/nhan-su-tg.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INhanSuTGProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class NhanSuTG extends React.Component<INhanSuTGProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { nhanSuTGList, match } = this.props;
    return (
      <div>
        <h2 id="nhan-su-tg-heading">
          <Translate contentKey="jhispterDemoApp.nhanSuTG.home.title">Nhan Su TGS</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.nhanSuTG.home.createLabel">Create a new Nhan Su TG</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {nhanSuTGList && nhanSuTGList.length > 0 ? (
            <Table responsive aria-describedby="nhan-su-tg-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.nhanSuTG.vaiTro">Vai Tro</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.nhanSuTG.officer">Officer</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.nhanSuTG.deTai">De Tai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {nhanSuTGList.map((nhanSuTG, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${nhanSuTG.id}`} color="link" size="sm">
                        {nhanSuTG.id}
                      </Button>
                    </td>
                    <td>{nhanSuTG.vaiTro}</td>
                    <td>{nhanSuTG.officer ? <Link to={`officer/${nhanSuTG.officer.id}`}>{nhanSuTG.officer.id}</Link> : ''}</td>
                    <td>{nhanSuTG.deTai ? <Link to={`de-tai/${nhanSuTG.deTai.id}`}>{nhanSuTG.deTai.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${nhanSuTG.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${nhanSuTG.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${nhanSuTG.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.nhanSuTG.home.notFound">No Nhan Su TGS found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ nhanSuTG }: IRootState) => ({
  nhanSuTGList: nhanSuTG.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NhanSuTG);
