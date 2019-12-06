import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NhanSuTG from './nhan-su-tg';
import NhanSuTGDetail from './nhan-su-tg-detail';
import NhanSuTGUpdate from './nhan-su-tg-update';
import NhanSuTGDeleteDialog from './nhan-su-tg-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NhanSuTGUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NhanSuTGUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NhanSuTGDetail} />
      <ErrorBoundaryRoute path={match.url} component={NhanSuTG} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NhanSuTGDeleteDialog} />
  </>
);

export default Routes;
