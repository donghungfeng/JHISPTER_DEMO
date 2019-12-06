import { IDeTai } from 'app/shared/model/de-tai.model';

export interface IXepLoai {
  id?: number;
  tenXepLoai?: string;
  deTais?: IDeTai[];
}

export const defaultValue: Readonly<IXepLoai> = {};
