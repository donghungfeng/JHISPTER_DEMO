import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './danh-gia.reducer';
import { IDanhGia } from 'app/shared/model/danh-gia.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDanhGiaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DanhGiaDetail extends React.Component<IDanhGiaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { danhGiaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.danhGia.detail.title">DanhGia</Translate> [<b>{danhGiaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="loaiDanhGia">
                <Translate contentKey="jhispterDemoApp.danhGia.loaiDanhGia">Loai Danh Gia</Translate>
              </span>
            </dt>
            <dd>{danhGiaEntity.loaiDanhGia}</dd>
            <dt>
              <span id="diemDanhGia">
                <Translate contentKey="jhispterDemoApp.danhGia.diemDanhGia">Diem Danh Gia</Translate>
              </span>
            </dt>
            <dd>{danhGiaEntity.diemDanhGia}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.danhGia.deTai">De Tai</Translate>
            </dt>
            <dd>{danhGiaEntity.deTai ? danhGiaEntity.deTai.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/danh-gia" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/danh-gia/${danhGiaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ danhGia }: IRootState) => ({
  danhGiaEntity: danhGia.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DanhGiaDetail);
