import { IDeTai } from 'app/shared/model/de-tai.model';

export interface ICapDeTai {
  id?: number;
  tenCapDeTai?: string;
  deTais?: IDeTai[];
}

export const defaultValue: Readonly<ICapDeTai> = {};
