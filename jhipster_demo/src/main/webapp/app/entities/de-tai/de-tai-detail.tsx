import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './de-tai.reducer';
import { IDeTai } from 'app/shared/model/de-tai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDeTaiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DeTaiDetail extends React.Component<IDeTaiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { deTaiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.deTai.detail.title">DeTai</Translate> [<b>{deTaiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maDeTai">
                <Translate contentKey="jhispterDemoApp.deTai.maDeTai">Ma De Tai</Translate>
              </span>
            </dt>
            <dd>{deTaiEntity.maDeTai}</dd>
            <dt>
              <span id="tenDeTai">
                <Translate contentKey="jhispterDemoApp.deTai.tenDeTai">Ten De Tai</Translate>
              </span>
            </dt>
            <dd>{deTaiEntity.tenDeTai}</dd>
            <dt>
              <span id="mucTieu">
                <Translate contentKey="jhispterDemoApp.deTai.mucTieu">Muc Tieu</Translate>
              </span>
            </dt>
            <dd>{deTaiEntity.mucTieu}</dd>
            <dt>
              <span id="ngayBDDuKien">
                <Translate contentKey="jhispterDemoApp.deTai.ngayBDDuKien">Ngay BD Du Kien</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={deTaiEntity.ngayBDDuKien} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ngayKTDuKien">
                <Translate contentKey="jhispterDemoApp.deTai.ngayKTDuKien">Ngay KT Du Kien</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={deTaiEntity.ngayKTDuKien} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="kinhPhiDuKien">
                <Translate contentKey="jhispterDemoApp.deTai.kinhPhiDuKien">Kinh Phi Du Kien</Translate>
              </span>
            </dt>
            <dd>{deTaiEntity.kinhPhiDuKien}</dd>
            <dt>
              <span id="noiDungTongQuan">
                <Translate contentKey="jhispterDemoApp.deTai.noiDungTongQuan">Noi Dung Tong Quan</Translate>
              </span>
            </dt>
            <dd>{deTaiEntity.noiDungTongQuan}</dd>
            <dt>
              <span id="kinhPhiHoanThanh">
                <Translate contentKey="jhispterDemoApp.deTai.kinhPhiHoanThanh">Kinh Phi Hoan Thanh</Translate>
              </span>
            </dt>
            <dd>{deTaiEntity.kinhPhiHoanThanh}</dd>
            <dt>
              <span id="tongDiem">
                <Translate contentKey="jhispterDemoApp.deTai.tongDiem">Tong Diem</Translate>
              </span>
            </dt>
            <dd>{deTaiEntity.tongDiem}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.deTai.officer">Officer</Translate>
            </dt>
            <dd>{deTaiEntity.officer ? deTaiEntity.officer.id : ''}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.deTai.trangThai">Trang Thai</Translate>
            </dt>
            <dd>{deTaiEntity.trangThai ? deTaiEntity.trangThai.id : ''}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.deTai.capDeTai">Cap De Tai</Translate>
            </dt>
            <dd>{deTaiEntity.capDeTai ? deTaiEntity.capDeTai.id : ''}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.deTai.xepLoai">Xep Loai</Translate>
            </dt>
            <dd>{deTaiEntity.xepLoai ? deTaiEntity.xepLoai.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/de-tai" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/de-tai/${deTaiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ deTai }: IRootState) => ({
  deTaiEntity: deTai.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DeTaiDetail);
