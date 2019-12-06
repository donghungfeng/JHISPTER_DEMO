import { Moment } from 'moment';
import { IFileAttach } from 'app/shared/model/file-attach.model';
import { IDeTai } from 'app/shared/model/de-tai.model';

export interface ITienDo {
  id?: number;
  tienDoHoanThanh?: number;
  moTa?: string;
  ngayCapNhat?: Moment;
  fileAttaches?: IFileAttach[];
  deTai?: IDeTai;
}

export const defaultValue: Readonly<ITienDo> = {};
