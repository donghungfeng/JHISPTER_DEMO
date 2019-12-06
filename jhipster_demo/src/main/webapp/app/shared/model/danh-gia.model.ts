import { IDeTai } from 'app/shared/model/de-tai.model';

export interface IDanhGia {
  id?: number;
  loaiDanhGia?: number;
  diemDanhGia?: number;
  deTai?: IDeTai;
}

export const defaultValue: Readonly<IDanhGia> = {};
