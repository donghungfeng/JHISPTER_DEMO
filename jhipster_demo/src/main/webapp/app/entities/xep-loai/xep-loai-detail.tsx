import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './xep-loai.reducer';
import { IXepLoai } from 'app/shared/model/xep-loai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IXepLoaiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class XepLoaiDetail extends React.Component<IXepLoaiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { xepLoaiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.xepLoai.detail.title">XepLoai</Translate> [<b>{xepLoaiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tenXepLoai">
                <Translate contentKey="jhispterDemoApp.xepLoai.tenXepLoai">Ten Xep Loai</Translate>
              </span>
            </dt>
            <dd>{xepLoaiEntity.tenXepLoai}</dd>
          </dl>
          <Button tag={Link} to="/xep-loai" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/xep-loai/${xepLoaiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ xepLoai }: IRootState) => ({
  xepLoaiEntity: xepLoai.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(XepLoaiDetail);
