import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IXepLoai, defaultValue } from 'app/shared/model/xep-loai.model';

export const ACTION_TYPES = {
  FETCH_XEPLOAI_LIST: 'xepLoai/FETCH_XEPLOAI_LIST',
  FETCH_XEPLOAI: 'xepLoai/FETCH_XEPLOAI',
  CREATE_XEPLOAI: 'xepLoai/CREATE_XEPLOAI',
  UPDATE_XEPLOAI: 'xepLoai/UPDATE_XEPLOAI',
  DELETE_XEPLOAI: 'xepLoai/DELETE_XEPLOAI',
  RESET: 'xepLoai/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IXepLoai>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type XepLoaiState = Readonly<typeof initialState>;

// Reducer

export default (state: XepLoaiState = initialState, action): XepLoaiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_XEPLOAI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_XEPLOAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_XEPLOAI):
    case REQUEST(ACTION_TYPES.UPDATE_XEPLOAI):
    case REQUEST(ACTION_TYPES.DELETE_XEPLOAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_XEPLOAI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_XEPLOAI):
    case FAILURE(ACTION_TYPES.CREATE_XEPLOAI):
    case FAILURE(ACTION_TYPES.UPDATE_XEPLOAI):
    case FAILURE(ACTION_TYPES.DELETE_XEPLOAI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_XEPLOAI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_XEPLOAI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_XEPLOAI):
    case SUCCESS(ACTION_TYPES.UPDATE_XEPLOAI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_XEPLOAI):
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

const apiUrl = 'api/xep-loais';

// Actions

export const getEntities: ICrudGetAllAction<IXepLoai> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_XEPLOAI_LIST,
  payload: axios.get<IXepLoai>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IXepLoai> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_XEPLOAI,
    payload: axios.get<IXepLoai>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IXepLoai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_XEPLOAI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IXepLoai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_XEPLOAI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IXepLoai> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_XEPLOAI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
