import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ToChuc from './to-chuc';
import Officer from './officer';
import CapDeTai from './cap-de-tai';
import TrangThai from './trang-thai';
import XepLoai from './xep-loai';
import DanhGia from './danh-gia';
import NhanSuTG from './nhan-su-tg';
import DuToan from './du-toan';
import TienDo from './tien-do';
import FileAttach from './file-attach';
import DeTai from './de-tai';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}to-chuc`} component={ToChuc} />
      <ErrorBoundaryRoute path={`${match.url}officer`} component={Officer} />
      <ErrorBoundaryRoute path={`${match.url}cap-de-tai`} component={CapDeTai} />
      <ErrorBoundaryRoute path={`${match.url}trang-thai`} component={TrangThai} />
      <ErrorBoundaryRoute path={`${match.url}xep-loai`} component={XepLoai} />
      <ErrorBoundaryRoute path={`${match.url}danh-gia`} component={DanhGia} />
      <ErrorBoundaryRoute path={`${match.url}nhan-su-tg`} component={NhanSuTG} />
      <ErrorBoundaryRoute path={`${match.url}du-toan`} component={DuToan} />
      <ErrorBoundaryRoute path={`${match.url}tien-do`} component={TienDo} />
      <ErrorBoundaryRoute path={`${match.url}file-attach`} component={FileAttach} />
      <ErrorBoundaryRoute path={`${match.url}de-tai`} component={DeTai} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
