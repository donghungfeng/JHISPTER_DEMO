import { IDeTai } from 'app/shared/model/de-tai.model';

export interface ITrangThai {
  id?: number;
  tenTrangThai?: string;
  deTais?: IDeTai[];
}

export const defaultValue: Readonly<ITrangThai> = {};
