import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FileAttach from './file-attach';
import FileAttachDetail from './file-attach-detail';
import FileAttachUpdate from './file-attach-update';
import FileAttachDeleteDialog from './file-attach-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FileAttachUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FileAttachUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FileAttachDetail} />
      <ErrorBoundaryRoute path={match.url} component={FileAttach} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FileAttachDeleteDialog} />
  </>
);

export default Routes;
