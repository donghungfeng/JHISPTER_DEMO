import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFileAttach, defaultValue } from 'app/shared/model/file-attach.model';

export const ACTION_TYPES = {
  FETCH_FILEATTACH_LIST: 'fileAttach/FETCH_FILEATTACH_LIST',
  FETCH_FILEATTACH: 'fileAttach/FETCH_FILEATTACH',
  CREATE_FILEATTACH: 'fileAttach/CREATE_FILEATTACH',
  UPDATE_FILEATTACH: 'fileAttach/UPDATE_FILEATTACH',
  DELETE_FILEATTACH: 'fileAttach/DELETE_FILEATTACH',
  SET_BLOB: 'fileAttach/SET_BLOB',
  RESET: 'fileAttach/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFileAttach>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FileAttachState = Readonly<typeof initialState>;

// Reducer

export default (state: FileAttachState = initialState, action): FileAttachState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FILEATTACH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FILEATTACH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FILEATTACH):
    case REQUEST(ACTION_TYPES.UPDATE_FILEATTACH):
    case REQUEST(ACTION_TYPES.DELETE_FILEATTACH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FILEATTACH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FILEATTACH):
    case FAILURE(ACTION_TYPES.CREATE_FILEATTACH):
    case FAILURE(ACTION_TYPES.UPDATE_FILEATTACH):
    case FAILURE(ACTION_TYPES.DELETE_FILEATTACH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FILEATTACH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FILEATTACH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FILEATTACH):
    case SUCCESS(ACTION_TYPES.UPDATE_FILEATTACH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FILEATTACH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType
        }
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/file-attaches';

// Actions

export const getEntities: ICrudGetAllAction<IFileAttach> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FILEATTACH_LIST,
  payload: axios.get<IFileAttach>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFileAttach> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FILEATTACH,
    payload: axios.get<IFileAttach>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFileAttach> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FILEATTACH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFileAttach> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FILEATTACH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFileAttach> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FILEATTACH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType
  }
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
