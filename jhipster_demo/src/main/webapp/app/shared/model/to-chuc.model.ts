import { IOfficer } from 'app/shared/model/officer.model';

export interface IToChuc {
  id?: number;
  tenToChuc?: string;
  officers?: IOfficer[];
}

export const defaultValue: Readonly<IToChuc> = {};
