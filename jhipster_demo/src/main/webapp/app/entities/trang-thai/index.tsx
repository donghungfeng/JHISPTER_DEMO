import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TrangThai from './trang-thai';
import TrangThaiDetail from './trang-thai-detail';
import TrangThaiUpdate from './trang-thai-update';
import TrangThaiDeleteDialog from './trang-thai-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TrangThaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TrangThaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TrangThaiDetail} />
      <ErrorBoundaryRoute path={match.url} component={TrangThai} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TrangThaiDeleteDialog} />
  </>
);

export default Routes;
