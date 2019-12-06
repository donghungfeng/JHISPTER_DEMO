import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Officer from './officer';
import OfficerDetail from './officer-detail';
import OfficerUpdate from './officer-update';
import OfficerDeleteDialog from './officer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OfficerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OfficerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OfficerDetail} />
      <ErrorBoundaryRoute path={match.url} component={Officer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={OfficerDeleteDialog} />
  </>
);

export default Routes;
