import { IDeTai } from 'app/shared/model/de-tai.model';
import { INhanSuTG } from 'app/shared/model/nhan-su-tg.model';
import { IToChuc } from 'app/shared/model/to-chuc.model';

export interface IOfficer {
  id?: number;
  tenNhanSu?: string;
  dienThoai?: string;
  email?: string;
  fax?: string;
  diaChi?: string;
  deTais?: IDeTai[];
  nhanSuTGS?: INhanSuTG[];
  toChuc?: IToChuc;
}

export const defaultValue: Readonly<IOfficer> = {};
