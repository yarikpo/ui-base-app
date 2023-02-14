import { 
    ERROR_RECEIVE_CARD, 
    RECEIVE_CARD, 
    REQUEST_CARD, 
    SAVE_CARD, 
    UPDATE_CARD 
} from "../actions/actionTypes";

const initialState = {
    card: null,
    isLoading: false,
    isError: false,
    isRedirect: false,
};

export default (state = initialState, action) => {
    switch (action.type) {
        case REQUEST_CARD: {
            return {
                ...state,
                isLoading: true,
                isError: false,
                isRedirect: false,
            };
        }
        case RECEIVE_CARD: {
            return {
                ...state,
                isLoading: false,
                isError: false,
                isRedirect: false,
                card: action.card,
            };
        }
        case UPDATE_CARD: {
            return {
                ...state,
                isLoading: false,
                isError: false,
                isRedirect: true,
            };
        }
        case SAVE_CARD: {
            return {
                ...state,
                isLoading: false,
                isError: false,
                isRedirect: true,
            };
        }
        case ERROR_RECEIVE_CARD: {
            return {
                ...state,
                isLoading: false,
                isError: true,
                isRedirect: false,
            };
        }

        default: return state;
    }
}