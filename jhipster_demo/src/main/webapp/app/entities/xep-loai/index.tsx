import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import XepLoai from './xep-loai';
import XepLoaiDetail from './xep-loai-detail';
import XepLoaiUpdate from './xep-loai-update';
import XepLoaiDeleteDialog from './xep-loai-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={XepLoaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={XepLoaiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={XepLoaiDetail} />
      <ErrorBoundaryRoute path={match.url} component={XepLoai} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={XepLoaiDeleteDialog} />
  </>
);

export default Routes;
