import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './to-chuc.reducer';
import { IToChuc } from 'app/shared/model/to-chuc.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IToChucDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ToChucDetail extends React.Component<IToChucDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { toChucEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.toChuc.detail.title">ToChuc</Translate> [<b>{toChucEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tenToChuc">
                <Translate contentKey="jhispterDemoApp.toChuc.tenToChuc">Ten To Chuc</Translate>
              </span>
            </dt>
            <dd>{toChucEntity.tenToChuc}</dd>
          </dl>
          <Button tag={Link} to="/to-chuc" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/to-chuc/${toChucEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ toChuc }: IRootState) => ({
  toChucEntity: toChuc.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ToChucDetail);
