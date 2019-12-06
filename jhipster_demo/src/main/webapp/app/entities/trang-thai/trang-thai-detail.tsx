import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './trang-thai.reducer';
import { ITrangThai } from 'app/shared/model/trang-thai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrangThaiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TrangThaiDetail extends React.Component<ITrangThaiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { trangThaiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.trangThai.detail.title">TrangThai</Translate> [<b>{trangThaiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tenTrangThai">
                <Translate contentKey="jhispterDemoApp.trangThai.tenTrangThai">Ten Trang Thai</Translate>
              </span>
            </dt>
            <dd>{trangThaiEntity.tenTrangThai}</dd>
          </dl>
          <Button tag={Link} to="/trang-thai" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/trang-thai/${trangThaiEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ trangThai }: IRootState) => ({
  trangThaiEntity: trangThai.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TrangThaiDetail);
