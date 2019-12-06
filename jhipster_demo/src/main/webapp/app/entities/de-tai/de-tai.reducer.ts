import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDeTai, defaultValue } from 'app/shared/model/de-tai.model';

export const ACTION_TYPES = {
  FETCH_DETAI_LIST: 'deTai/FETCH_DETAI_LIST',
  FETCH_DETAI: 'deTai/FETCH_DETAI',
  CREATE_DETAI: 'deTai/CREATE_DETAI',
  UPDATE_DETAI: 'deTai/UPDATE_DETAI',
  DELETE_DETAI: 'deTai/DELETE_DETAI',
  RESET: 'deTai/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDeTai>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DeTaiState = Readonly<typeof initialState>;

// Reducer

export default (state: DeTaiState = initialState, action): DeTaiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DETAI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DETAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DETAI):
    case REQUEST(ACTION_TYPES.UPDATE_DETAI):
    case REQUEST(ACTION_TYPES.DELETE_DETAI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DETAI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DETAI):
    case FAILURE(ACTION_TYPES.CREATE_DETAI):
    case FAILURE(ACTION_TYPES.UPDATE_DETAI):
    case FAILURE(ACTION_TYPES.DELETE_DETAI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DETAI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DETAI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DETAI):
    case SUCCESS(ACTION_TYPES.UPDATE_DETAI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DETAI):
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

const apiUrl = 'api/de-tais';

// Actions

export const getEntities: ICrudGetAllAction<IDeTai> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DETAI_LIST,
  payload: axios.get<IDeTai>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDeTai> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DETAI,
    payload: axios.get<IDeTai>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDeTai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DETAI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDeTai> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DETAI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDeTai> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DETAI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
