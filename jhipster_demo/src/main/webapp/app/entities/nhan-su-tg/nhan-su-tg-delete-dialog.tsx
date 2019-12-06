import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { INhanSuTG } from 'app/shared/model/nhan-su-tg.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './nhan-su-tg.reducer';

export interface INhanSuTGDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NhanSuTGDeleteDialog extends React.Component<INhanSuTGDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.nhanSuTGEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { nhanSuTGEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="jhispterDemoApp.nhanSuTG.delete.question">
          <Translate contentKey="jhispterDemoApp.nhanSuTG.delete.question" interpolate={{ id: nhanSuTGEntity.id }}>
            Are you sure you want to delete this NhanSuTG?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-nhanSuTG" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ nhanSuTG }: IRootState) => ({
  nhanSuTGEntity: nhanSuTG.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NhanSuTGDeleteDialog);
