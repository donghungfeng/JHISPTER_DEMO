import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDanhGia, defaultValue } from 'app/shared/model/danh-gia.model';

export const ACTION_TYPES = {
  FETCH_DANHGIA_LIST: 'danhGia/FETCH_DANHGIA_LIST',
  FETCH_DANHGIA: 'danhGia/FETCH_DANHGIA',
  CREATE_DANHGIA: 'danhGia/CREATE_DANHGIA',
  UPDATE_DANHGIA: 'danhGia/UPDATE_DANHGIA',
  DELETE_DANHGIA: 'danhGia/DELETE_DANHGIA',
  RESET: 'danhGia/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDanhGia>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DanhGiaState = Readonly<typeof initialState>;

// Reducer

export default (state: DanhGiaState = initialState, action): DanhGiaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DANHGIA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DANHGIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DANHGIA):
    case REQUEST(ACTION_TYPES.UPDATE_DANHGIA):
    case REQUEST(ACTION_TYPES.DELETE_DANHGIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DANHGIA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DANHGIA):
    case FAILURE(ACTION_TYPES.CREATE_DANHGIA):
    case FAILURE(ACTION_TYPES.UPDATE_DANHGIA):
    case FAILURE(ACTION_TYPES.DELETE_DANHGIA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DANHGIA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DANHGIA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DANHGIA):
    case SUCCESS(ACTION_TYPES.UPDATE_DANHGIA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DANHGIA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/danh-gias';

// Actions

export const getEntities: ICrudGetAllAction<IDanhGia> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DANHGIA_LIST,
  payload: axios.get<IDanhGia>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDanhGia> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DANHGIA,
    payload: axios.get<IDanhGia>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDanhGia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DANHGIA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDanhGia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DANHGIA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDanhGia> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DANHGIA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
