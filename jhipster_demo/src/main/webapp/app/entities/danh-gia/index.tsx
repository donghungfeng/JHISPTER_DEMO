import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DanhGia from './danh-gia';
import DanhGiaDetail from './danh-gia-detail';
import DanhGiaUpdate from './danh-gia-update';
import DanhGiaDeleteDialog from './danh-gia-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DanhGiaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DanhGiaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DanhGiaDetail} />
      <ErrorBoundaryRoute path={match.url} component={DanhGia} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DanhGiaDeleteDialog} />
  </>
);

export default Routes;
