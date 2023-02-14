import { getJson, putJson, postJson } from "requests";
import { 
    REQUEST_CARD, 
    RECEIVE_CARD, 
    UPDATE_CARD, 
    SAVE_CARD, 
    ERROR_RECEIVE_CARD 
} from "./actionTypes";
import Config from "config";

const requestCard = () => ({
    type: REQUEST_CARD,
});
const receiveCard = (card) => ({
    type: RECEIVE_CARD,
    card,
});
const updateCard = () => ({
    type: UPDATE_CARD,
});
const saveCard = () => ({
    type: SAVE_CARD,
});
const errorReceiveCard = () => ({
    type: ERROR_RECEIVE_CARD,
});

const receiveCardById = ({ cardId }) => (dispatch) => {
    dispatch(requestCard());
    const url = `${Config.BASE_URL}${Config.CARD_SERVICE}/${cardId}`;
    return getJson({ url })
            .then((data) => dispatch(receiveCard(data)))
            .catch(() => dispatch(errorReceiveCard()));
};

const updateCardById = ({ cardId, card }) => (dispatch) => {
    dispatch(requestCard());
    const url = `${Config.BASE_URL}${Config.CARD_SERVICE}/${cardId}`;
    return putJson({ url, cardId, card })
            .then((data) => dispatch(updateCard()))
            .catch(() => dispatch(errorReceiveCard()));
};

const saveNewCard = ({ card }) => (dispatch) => {
    dispatch(requestCard());
    const url = `${Config.BASE_URL}${Config.CARD_SERVICE}`;
    return postJson({
        url,
        body: card,
    }).then((data) => dispatch(saveCard()))
    .catch(() => dispatch(errorReceiveCard()));
};

export default {
    receiveCardById,
    updateCardById,
    saveNewCard,
};