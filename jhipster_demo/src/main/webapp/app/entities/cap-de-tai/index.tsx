import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CapDeTai from './cap-de-tai';
import CapDeTaiDetail from './cap-de-tai-detail';
import CapDeTaiUpdate from './cap-de-tai-update';
import CapDeTaiDeleteDialog from './cap-de-tai-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CapDeTaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CapDeTaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CapDeTaiDetail} />
      <ErrorBoundaryRoute path={match.url} component={CapDeTai} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CapDeTaiDeleteDialog} />
  </>
);

export default Routes;
