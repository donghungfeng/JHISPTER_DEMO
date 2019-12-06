import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INhanSuTG, defaultValue } from 'app/shared/model/nhan-su-tg.model';

export const ACTION_TYPES = {
  FETCH_NHANSUTG_LIST: 'nhanSuTG/FETCH_NHANSUTG_LIST',
  FETCH_NHANSUTG: 'nhanSuTG/FETCH_NHANSUTG',
  CREATE_NHANSUTG: 'nhanSuTG/CREATE_NHANSUTG',
  UPDATE_NHANSUTG: 'nhanSuTG/UPDATE_NHANSUTG',
  DELETE_NHANSUTG: 'nhanSuTG/DELETE_NHANSUTG',
  RESET: 'nhanSuTG/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INhanSuTG>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NhanSuTGState = Readonly<typeof initialState>;

// Reducer

export default (state: NhanSuTGState = initialState, action): NhanSuTGState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NHANSUTG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NHANSUTG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NHANSUTG):
    case REQUEST(ACTION_TYPES.UPDATE_NHANSUTG):
    case REQUEST(ACTION_TYPES.DELETE_NHANSUTG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NHANSUTG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NHANSUTG):
    case FAILURE(ACTION_TYPES.CREATE_NHANSUTG):
    case FAILURE(ACTION_TYPES.UPDATE_NHANSUTG):
    case FAILURE(ACTION_TYPES.DELETE_NHANSUTG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANSUTG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANSUTG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NHANSUTG):
    case SUCCESS(ACTION_TYPES.UPDATE_NHANSUTG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NHANSUTG):
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

const apiUrl = 'api/nhan-su-tgs';

// Actions

export const getEntities: ICrudGetAllAction<INhanSuTG> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NHANSUTG_LIST,
  payload: axios.get<INhanSuTG>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INhanSuTG> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NHANSUTG,
    payload: axios.get<INhanSuTG>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INhanSuTG> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NHANSUTG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INhanSuTG> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NHANSUTG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INhanSuTG> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NHANSUTG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
