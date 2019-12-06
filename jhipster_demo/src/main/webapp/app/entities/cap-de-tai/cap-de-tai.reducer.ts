import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICapDeTai, defaultValue } from 'app/shared/model/cap-de-tai.model';

export const ACTION_TYPES = {
  FETCH_CAPDETAI_LIST: 'capDeTai/FETCH_CAPDETAI_LIST',
  FETCH_CAPDETAI: 'capDeTai/FETCH_CAPDETAI',
  CREATE_CAPDETAI: 'capDeTai/CREATE_CAPDETAI',
  UPDATE_CAPDETAI: 'capDeTai/UPDATE_CAPDETAI',
  DELETE_CAPDETAI: 'capDeTai/DELETE_CAPDETAI',
  RESET: 'capDeTai/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICapDeTai>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CapDeTaiState = Readonly<typeof initialState>;

// Reducer

export default (state: CapDeTaiState = initialState, action): CapDeTaiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CAPDETAI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAPDETAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAPDETAI):
    case REQUEST(ACTION_TYPES.UPDATE_CAPDETAI):
    case REQUEST(ACTION_TYPES.DELETE_CAPDETAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CAPDETAI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAPDETAI):
    case FAILURE(ACTION_TYPES.CREATE_CAPDETAI):
    case FAILURE(ACTION_TYPES.UPDATE_CAPDETAI):
    case FAILURE(ACTION_TYPES.DELETE_CAPDETAI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAPDETAI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAPDETAI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAPDETAI):
    case SUCCESS(ACTION_TYPES.UPDATE_CAPDETAI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAPDETAI):
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

const apiUrl = 'api/cap-de-tais';

// Actions

export const getEntities: ICrudGetAllAction<ICapDeTai> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CAPDETAI_LIST,
  payload: axios.get<ICapDeTai>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICapDeTai> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAPDETAI,
    payload: axios.get<ICapDeTai>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICapDeTai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAPDETAI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICapDeTai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAPDETAI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICapDeTai> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAPDETAI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
