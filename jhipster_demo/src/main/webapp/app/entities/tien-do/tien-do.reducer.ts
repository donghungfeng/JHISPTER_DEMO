import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITienDo, defaultValue } from 'app/shared/model/tien-do.model';

export const ACTION_TYPES = {
  FETCH_TIENDO_LIST: 'tienDo/FETCH_TIENDO_LIST',
  FETCH_TIENDO: 'tienDo/FETCH_TIENDO',
  CREATE_TIENDO: 'tienDo/CREATE_TIENDO',
  UPDATE_TIENDO: 'tienDo/UPDATE_TIENDO',
  DELETE_TIENDO: 'tienDo/DELETE_TIENDO',
  RESET: 'tienDo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITienDo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TienDoState = Readonly<typeof initialState>;

// Reducer

export default (state: TienDoState = initialState, action): TienDoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TIENDO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TIENDO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TIENDO):
    case REQUEST(ACTION_TYPES.UPDATE_TIENDO):
    case REQUEST(ACTION_TYPES.DELETE_TIENDO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TIENDO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TIENDO):
    case FAILURE(ACTION_TYPES.CREATE_TIENDO):
    case FAILURE(ACTION_TYPES.UPDATE_TIENDO):
    case FAILURE(ACTION_TYPES.DELETE_TIENDO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIENDO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIENDO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TIENDO):
    case SUCCESS(ACTION_TYPES.UPDATE_TIENDO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TIENDO):
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

const apiUrl = 'api/tien-dos';

// Actions

export const getEntities: ICrudGetAllAction<ITienDo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TIENDO_LIST,
  payload: axios.get<ITienDo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITienDo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TIENDO,
    payload: axios.get<ITienDo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITienDo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TIENDO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITienDo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TIENDO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITienDo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TIENDO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
