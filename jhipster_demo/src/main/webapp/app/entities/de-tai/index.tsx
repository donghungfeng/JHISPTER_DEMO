import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DeTai from './de-tai';
import DeTaiDetail from './de-tai-detail';
import DeTaiUpdate from './de-tai-update';
import DeTaiDeleteDialog from './de-tai-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DeTaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DeTaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DeTaiDetail} />
      <ErrorBoundaryRoute path={match.url} component={DeTai} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DeTaiDeleteDialog} />
  </>
);

export default Routes;
