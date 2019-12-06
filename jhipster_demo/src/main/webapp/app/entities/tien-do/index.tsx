import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TienDo from './tien-do';
import TienDoDetail from './tien-do-detail';
import TienDoUpdate from './tien-do-update';
import TienDoDeleteDialog from './tien-do-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TienDoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TienDoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TienDoDetail} />
      <ErrorBoundaryRoute path={match.url} component={TienDo} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TienDoDeleteDialog} />
  </>
);

export default Routes;
