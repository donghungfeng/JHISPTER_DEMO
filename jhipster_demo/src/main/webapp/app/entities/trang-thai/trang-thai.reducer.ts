import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITrangThai, defaultValue } from 'app/shared/model/trang-thai.model';

export const ACTION_TYPES = {
  FETCH_TRANGTHAI_LIST: 'trangThai/FETCH_TRANGTHAI_LIST',
  FETCH_TRANGTHAI: 'trangThai/FETCH_TRANGTHAI',
  CREATE_TRANGTHAI: 'trangThai/CREATE_TRANGTHAI',
  UPDATE_TRANGTHAI: 'trangThai/UPDATE_TRANGTHAI',
  DELETE_TRANGTHAI: 'trangThai/DELETE_TRANGTHAI',
  RESET: 'trangThai/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITrangThai>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TrangThaiState = Readonly<typeof initialState>;

// Reducer

export default (state: TrangThaiState = initialState, action): TrangThaiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRANGTHAI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRANGTHAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TRANGTHAI):
    case REQUEST(ACTION_TYPES.UPDATE_TRANGTHAI):
    case REQUEST(ACTION_TYPES.DELETE_TRANGTHAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TRANGTHAI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRANGTHAI):
    case FAILURE(ACTION_TYPES.CREATE_TRANGTHAI):
    case FAILURE(ACTION_TYPES.UPDATE_TRANGTHAI):
    case FAILURE(ACTION_TYPES.DELETE_TRANGTHAI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANGTHAI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANGTHAI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRANGTHAI):
    case SUCCESS(ACTION_TYPES.UPDATE_TRANGTHAI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRANGTHAI):
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

const apiUrl = 'api/trang-thais';

// Actions

export const getEntities: ICrudGetAllAction<ITrangThai> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRANGTHAI_LIST,
  payload: axios.get<ITrangThai>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITrangThai> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRANGTHAI,
    payload: axios.get<ITrangThai>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITrangThai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRANGTHAI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITrangThai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRANGTHAI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITrangThai> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRANGTHAI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
