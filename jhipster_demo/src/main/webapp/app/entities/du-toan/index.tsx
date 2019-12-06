import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DuToan from './du-toan';
import DuToanDetail from './du-toan-detail';
import DuToanUpdate from './du-toan-update';
import DuToanDeleteDialog from './du-toan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DuToanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DuToanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DuToanDetail} />
      <ErrorBoundaryRoute path={match.url} component={DuToan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DuToanDeleteDialog} />
  </>
);

export default Routes;
