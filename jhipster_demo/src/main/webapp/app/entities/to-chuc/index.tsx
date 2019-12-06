import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ToChuc from './to-chuc';
import ToChucDetail from './to-chuc-detail';
import ToChucUpdate from './to-chuc-update';
import ToChucDeleteDialog from './to-chuc-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ToChucUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ToChucUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ToChucDetail} />
      <ErrorBoundaryRoute path={match.url} component={ToChuc} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ToChucDeleteDialog} />
  </>
);

export default Routes;
