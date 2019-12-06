import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './du-toan.reducer';
import { IDuToan } from 'app/shared/model/du-toan.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDuToanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DuToanDetail extends React.Component<IDuToanDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { duToanEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.duToan.detail.title">DuToan</Translate> [<b>{duToanEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="loaiDuToan">
                <Translate contentKey="jhispterDemoApp.duToan.loaiDuToan">Loai Du Toan</Translate>
              </span>
            </dt>
            <dd>{duToanEntity.loaiDuToan}</dd>
            <dt>
              <span id="duToan">
                <Translate contentKey="jhispterDemoApp.duToan.duToan">Du Toan</Translate>
              </span>
            </dt>
            <dd>{duToanEntity.duToan}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.duToan.deTai">De Tai</Translate>
            </dt>
            <dd>{duToanEntity.deTai ? duToanEntity.deTai.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/du-toan" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/du-toan/${duToanEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ duToan }: IRootState) => ({
  duToanEntity: duToan.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DuToanDetail);
