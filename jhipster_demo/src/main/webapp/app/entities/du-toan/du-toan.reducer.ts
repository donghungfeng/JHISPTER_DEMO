import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDuToan, defaultValue } from 'app/shared/model/du-toan.model';

export const ACTION_TYPES = {
  FETCH_DUTOAN_LIST: 'duToan/FETCH_DUTOAN_LIST',
  FETCH_DUTOAN: 'duToan/FETCH_DUTOAN',
  CREATE_DUTOAN: 'duToan/CREATE_DUTOAN',
  UPDATE_DUTOAN: 'duToan/UPDATE_DUTOAN',
  DELETE_DUTOAN: 'duToan/DELETE_DUTOAN',
  RESET: 'duToan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDuToan>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DuToanState = Readonly<typeof initialState>;

// Reducer

export default (state: DuToanState = initialState, action): DuToanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DUTOAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DUTOAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DUTOAN):
    case REQUEST(ACTION_TYPES.UPDATE_DUTOAN):
    case REQUEST(ACTION_TYPES.DELETE_DUTOAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DUTOAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DUTOAN):
    case FAILURE(ACTION_TYPES.CREATE_DUTOAN):
    case FAILURE(ACTION_TYPES.UPDATE_DUTOAN):
    case FAILURE(ACTION_TYPES.DELETE_DUTOAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DUTOAN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DUTOAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DUTOAN):
    case SUCCESS(ACTION_TYPES.UPDATE_DUTOAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DUTOAN):
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

const apiUrl = 'api/du-toans';

// Actions

export const getEntities: ICrudGetAllAction<IDuToan> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DUTOAN_LIST,
  payload: axios.get<IDuToan>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDuToan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DUTOAN,
    payload: axios.get<IDuToan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDuToan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DUTOAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDuToan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DUTOAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDuToan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DUTOAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
