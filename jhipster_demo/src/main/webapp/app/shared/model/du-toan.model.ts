import { IDeTai } from 'app/shared/model/de-tai.model';

export interface IDuToan {
  id?: number;
  loaiDuToan?: number;
  duToan?: number;
  deTai?: IDeTai;
}

export const defaultValue: Readonly<IDuToan> = {};
