import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOfficer, defaultValue } from 'app/shared/model/officer.model';

export const ACTION_TYPES = {
  FETCH_OFFICER_LIST: 'officer/FETCH_OFFICER_LIST',
  FETCH_OFFICER: 'officer/FETCH_OFFICER',
  CREATE_OFFICER: 'officer/CREATE_OFFICER',
  UPDATE_OFFICER: 'officer/UPDATE_OFFICER',
  DELETE_OFFICER: 'officer/DELETE_OFFICER',
  RESET: 'officer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOfficer>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type OfficerState = Readonly<typeof initialState>;

// Reducer

export default (state: OfficerState = initialState, action): OfficerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_OFFICER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_OFFICER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_OFFICER):
    case REQUEST(ACTION_TYPES.UPDATE_OFFICER):
    case REQUEST(ACTION_TYPES.DELETE_OFFICER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_OFFICER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_OFFICER):
    case FAILURE(ACTION_TYPES.CREATE_OFFICER):
    case FAILURE(ACTION_TYPES.UPDATE_OFFICER):
    case FAILURE(ACTION_TYPES.DELETE_OFFICER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFICER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFICER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_OFFICER):
    case SUCCESS(ACTION_TYPES.UPDATE_OFFICER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_OFFICER):
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

const apiUrl = 'api/officers';

// Actions

export const getEntities: ICrudGetAllAction<IOfficer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFICER_LIST,
  payload: axios.get<IOfficer>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IOfficer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_OFFICER,
    payload: axios.get<IOfficer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IOfficer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_OFFICER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOfficer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_OFFICER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOfficer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_OFFICER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
