import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import toChuc, {
  ToChucState
} from 'app/entities/to-chuc/to-chuc.reducer';
// prettier-ignore
import officer, {
  OfficerState
} from 'app/entities/officer/officer.reducer';
// prettier-ignore
import capDeTai, {
  CapDeTaiState
} from 'app/entities/cap-de-tai/cap-de-tai.reducer';
// prettier-ignore
import trangThai, {
  TrangThaiState
} from 'app/entities/trang-thai/trang-thai.reducer';
// prettier-ignore
import xepLoai, {
  XepLoaiState
} from 'app/entities/xep-loai/xep-loai.reducer';
// prettier-ignore
import danhGia, {
  DanhGiaState
} from 'app/entities/danh-gia/danh-gia.reducer';
// prettier-ignore
import nhanSuTG, {
  NhanSuTGState
} from 'app/entities/nhan-su-tg/nhan-su-tg.reducer';
// prettier-ignore
import duToan, {
  DuToanState
} from 'app/entities/du-toan/du-toan.reducer';
// prettier-ignore
import tienDo, {
  TienDoState
} from 'app/entities/tien-do/tien-do.reducer';
// prettier-ignore
import fileAttach, {
  FileAttachState
} from 'app/entities/file-attach/file-attach.reducer';
// prettier-ignore
import deTai, {
  DeTaiState
} from 'app/entities/de-tai/de-tai.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly toChuc: ToChucState;
  readonly officer: OfficerState;
  readonly capDeTai: CapDeTaiState;
  readonly trangThai: TrangThaiState;
  readonly xepLoai: XepLoaiState;
  readonly danhGia: DanhGiaState;
  readonly nhanSuTG: NhanSuTGState;
  readonly duToan: DuToanState;
  readonly tienDo: TienDoState;
  readonly fileAttach: FileAttachState;
  readonly deTai: DeTaiState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  toChuc,
  officer,
  capDeTai,
  trangThai,
  xepLoai,
  danhGia,
  nhanSuTG,
  duToan,
  tienDo,
  fileAttach,
  deTai,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
