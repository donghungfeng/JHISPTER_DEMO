import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cap-de-tai.reducer';
import { ICapDeTai } from 'app/shared/model/cap-de-tai.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICapDeTaiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CapDeTaiDetail extends React.Component<ICapDeTaiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { capDeTaiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhispterDemoApp.capDeTai.detail.title">CapDeTai</Translate> [<b>{capDeTaiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tenCapDeTai">
                <Translate contentKey="jhispterDemoApp.capDeTai.tenCapDeTai">Ten Cap De Tai</Translate>
              </span>
            </dt>
            <dd>{capDeTaiEntity.tenCapDeTai}</dd>
          </dl>
          <Button tag={Link} to="/cap-de-tai" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/cap-de-tai/${capDeTaiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ capDeTai }: IRootState) => ({
  capDeTaiEntity: capDeTai.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CapDeTaiDetail);
