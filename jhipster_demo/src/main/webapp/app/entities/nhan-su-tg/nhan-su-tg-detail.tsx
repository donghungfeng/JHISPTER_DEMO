import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nhan-su-tg.reducer';
import { INhanSuTG } from 'app/shared/model/nhan-su-tg.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INhanSuTGDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NhanSuTGDetail extends React.Component<INhanSuTGDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { nhanSuTGEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.nhanSuTG.detail.title">NhanSuTG</Translate> [<b>{nhanSuTGEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="vaiTro">
                <Translate contentKey="jhispterDemoApp.nhanSuTG.vaiTro">Vai Tro</Translate>
              </span>
            </dt>
            <dd>{nhanSuTGEntity.vaiTro}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.nhanSuTG.officer">Officer</Translate>
            </dt>
            <dd>{nhanSuTGEntity.officer ? nhanSuTGEntity.officer.id : ''}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.nhanSuTG.deTai">De Tai</Translate>
            </dt>
            <dd>{nhanSuTGEntity.deTai ? nhanSuTGEntity.deTai.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/nhan-su-tg" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/nhan-su-tg/${nhanSuTGEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ nhanSuTG }: IRootState) => ({
  nhanSuTGEntity: nhanSuTG.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NhanSuTGDetail);
