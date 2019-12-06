import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IToChuc, defaultValue } from 'app/shared/model/to-chuc.model';

export const ACTION_TYPES = {
  FETCH_TOCHUC_LIST: 'toChuc/FETCH_TOCHUC_LIST',
  FETCH_TOCHUC: 'toChuc/FETCH_TOCHUC',
  CREATE_TOCHUC: 'toChuc/CREATE_TOCHUC',
  UPDATE_TOCHUC: 'toChuc/UPDATE_TOCHUC',
  DELETE_TOCHUC: 'toChuc/DELETE_TOCHUC',
  RESET: 'toChuc/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IToChuc>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ToChucState = Readonly<typeof initialState>;

// Reducer

export default (state: ToChucState = initialState, action): ToChucState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TOCHUC_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TOCHUC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TOCHUC):
    case REQUEST(ACTION_TYPES.UPDATE_TOCHUC):
    case REQUEST(ACTION_TYPES.DELETE_TOCHUC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TOCHUC_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TOCHUC):
    case FAILURE(ACTION_TYPES.CREATE_TOCHUC):
    case FAILURE(ACTION_TYPES.UPDATE_TOCHUC):
    case FAILURE(ACTION_TYPES.DELETE_TOCHUC):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TOCHUC_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TOCHUC):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TOCHUC):
    case SUCCESS(ACTION_TYPES.UPDATE_TOCHUC):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TOCHUC):
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

const apiUrl = 'api/to-chucs';

// Actions

export const getEntities: ICrudGetAllAction<IToChuc> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TOCHUC_LIST,
  payload: axios.get<IToChuc>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IToChuc> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TOCHUC,
    payload: axios.get<IToChuc>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IToChuc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TOCHUC,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IToChuc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TOCHUC,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IToChuc> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TOCHUC,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
