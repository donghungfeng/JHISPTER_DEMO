import { Moment } from 'moment';
import { IDeTai } from 'app/shared/model/de-tai.model';
import { ITienDo } from 'app/shared/model/tien-do.model';

export interface IFileAttach {
  id?: number;
  moTa?: string;
  noiDungContentType?: string;
  noiDung?: any;
  ngayCapNhat?: Moment;
  deTai?: IDeTai;
  tienDo?: ITienDo;
}

export const defaultValue: Readonly<IFileAttach> = {};
