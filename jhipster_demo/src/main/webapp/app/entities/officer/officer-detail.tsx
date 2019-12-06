import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './officer.reducer';
import { IOfficer } from 'app/shared/model/officer.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfficerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class OfficerDetail extends React.Component<IOfficerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { officerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.officer.detail.title">Officer</Translate> [<b>{officerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tenNhanSu">
                <Translate contentKey="jhispterDemoApp.officer.tenNhanSu">Ten Nhan Su</Translate>
              </span>
            </dt>
            <dd>{officerEntity.tenNhanSu}</dd>
            <dt>
              <span id="dienThoai">
                <Translate contentKey="jhispterDemoApp.officer.dienThoai">Dien Thoai</Translate>
              </span>
            </dt>
            <dd>{officerEntity.dienThoai}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="jhispterDemoApp.officer.email">Email</Translate>
              </span>
            </dt>
            <dd>{officerEntity.email}</dd>
            <dt>
              <span id="fax">
                <Translate contentKey="jhispterDemoApp.officer.fax">Fax</Translate>
              </span>
            </dt>
            <dd>{officerEntity.fax}</dd>
            <dt>
              <span id="diaChi">
                <Translate contentKey="jhispterDemoApp.officer.diaChi">Dia Chi</Translate>
              </span>
            </dt>
            <dd>{officerEntity.diaChi}</dd>
            <dt>
              <Translate contentKey="jhispterDemoApp.officer.toChuc">To Chuc</Translate>
            </dt>
            <dd>{officerEntity.toChuc ? officerEntity.toChuc.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/officer" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/officer/${officerEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ officer }: IRootState) => ({
  officerEntity: officer.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OfficerDetail);
