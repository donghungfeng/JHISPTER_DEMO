import { IOfficer } from 'app/shared/model/officer.model';
import { IDeTai } from 'app/shared/model/de-tai.model';

export interface INhanSuTG {
  id?: number;
  vaiTro?: string;
  officer?: IOfficer;
  deTai?: IDeTai;
}

export const defaultValue: Readonly<INhanSuTG> = {};
