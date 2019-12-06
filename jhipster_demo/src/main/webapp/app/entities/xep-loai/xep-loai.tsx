import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './xep-loai.reducer';
import { IXepLoai } from 'app/shared/model/xep-loai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IXepLoaiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class XepLoai extends React.Component<IXepLoaiProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { xepLoaiList, match } = this.props;
    return (
      <div>
        <h2 id="xep-loai-heading">
          <Translate contentKey="jhispterDemoApp.xepLoai.home.title">Xep Loais</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhispterDemoApp.xepLoai.home.createLabel">Create a new Xep Loai</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {xepLoaiList && xepLoaiList.length > 0 ? (
            <Table responsive aria-describedby="xep-loai-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="jhispterDemoApp.xepLoai.tenXepLoai">Ten Xep Loai</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {xepLoaiList.map((xepLoai, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${xepLoai.id}`} color="link" size="sm">
                        {xepLoai.id}
                      </Button>
                    </td>
                    <td>{xepLoai.tenXepLoai}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${xepLoai.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${xepLoai.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${xepLoai.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhispterDemoApp.xepLoai.home.notFound">No Xep Loais found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ xepLoai }: IRootState) => ({
  xepLoaiList: xepLoai.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(XepLoai);
