import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tien-do.reducer';
import { ITienDo } from 'app/shared/model/tien-do.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITienDoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TienDoDetail extends React.Component<ITienDoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tienDoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.tienDo.detail.title">TienDo</Translate> [<b>{tienDoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tienDoHoanThanh">
                <Translate contentKey="jhispterDemoApp.tienDo.tienDoHoanThanh">Tien Do Hoan Thanh</Translate>
              </span>
            </dt>
            <dd>{tienDoEntity.tienDoHoanThanh}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="jhispterDemoApp.tienDo.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{tienDoEntity.moTa}</dd>
            <dt>
              <span id="ngayCapNhat">
                <Translate contentKey="jhispterDemoApp.tienDo.ngayCapNhat">Ngay Cap Nhat</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={tienDoEntity.ngayCapNhat} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.tienDo.deTai">De Tai</Translate>
            </dt>
            <dd>{tienDoEntity.deTai ? tienDoEntity.deTai.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/tien-do" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/tien-do/${tienDoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ tienDo }: IRootState) => ({
  tienDoEntity: tienDo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TienDoDetail);
