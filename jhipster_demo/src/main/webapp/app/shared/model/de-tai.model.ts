import { Moment } from 'moment';
import { IDuToan } from 'app/shared/model/du-toan.model';
import { ITienDo } from 'app/shared/model/tien-do.model';
import { IDanhGia } from 'app/shared/model/danh-gia.model';
import { IFileAttach } from 'app/shared/model/file-attach.model';
import { INhanSuTG } from 'app/shared/model/nhan-su-tg.model';
import { IOfficer } from 'app/shared/model/officer.model';
import { ITrangThai } from 'app/shared/model/trang-thai.model';
import { ICapDeTai } from 'app/shared/model/cap-de-tai.model';
import { IXepLoai } from 'app/shared/model/xep-loai.model';

export interface IDeTai {
  id?: number;
  maDeTai?: string;
  tenDeTai?: string;
  mucTieu?: string;
  ngayBDDuKien?: Moment;
  ngayKTDuKien?: Moment;
  kinhPhiDuKien?: number;
  noiDungTongQuan?: string;
  kinhPhiHoanThanh?: number;
  tongDiem?: number;
  duToans?: IDuToan[];
  tienDos?: ITienDo[];
  danhGias?: IDanhGia[];
  fileAttaches?: IFileAttach[];
  nhanSuTGS?: INhanSuTG[];
  officer?: IOfficer;
  trangThai?: ITrangThai;
  capDeTai?: ICapDeTai;
  xepLoai?: IXepLoai;
}

export const defaultValue: Readonly<IDeTai> = {};
