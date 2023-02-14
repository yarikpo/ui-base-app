import { 
    DELETE_CARD, 
    ERROR_RECEIVE_CARD, 
    RECEIVE_CARD, 
    REQUEST_CARD 
} from "../actions/actionTypes";

const initialState = {
    isLoading: false,
    isError: false,
    list: []
}

export default (state = initialState, action) => {
    switch (action.type) {
        case REQUEST_CARD: {
            return {
                ...state,
                isLoading: true,
                isError: false,
            };
        }
        case RECEIVE_CARD: {
            return {
                ...state,
                isLoading: false,
                isError: false,
                list: action.cards,
            };
        }
        case ERROR_RECEIVE_CARD: {
            return {
                ...state,
                isLoading: false,
                isError: true,
            };
        }
        case DELETE_CARD: {
            return {
                ...state,
                isLoading: false,
                isError: false,
                list: state.list.filter(
                    card => card.cardId !== action.card.cardId
                ),
            };
        }

        default: return state;
    }
}