import { getJson, fetchDelete } from "requests";
import { 
    REQUEST_CARD, 
    RECEIVE_CARD, 
    ERROR_RECEIVE_CARD, 
    DELETE_CARD 
} from "./actionTypes";
import Config from "config";

const requestCard = () => ({
    type: REQUEST_CARD,
});
const receiveCard = (cards) => ({
    type: RECEIVE_CARD,
    cards,
});
const errorReceiveCard = () => ({
    type: ERROR_RECEIVE_CARD,
});
const deleteCard = (card) => ({
    type: DELETE_CARD,
    card,
});

const receiveCards = () => (dispatch) => {
    dispatch(requestCard());
    const url = `${Config.BASE_URL}${Config.CARD_SERVICE}`;
    return getJson({ url })
                .then((data) => dispatch(receiveCard(data.cards)))
                .catch(() => dispatch(errorReceiveCard()));
}

const deleteCardById = ({ cardId }) => (dispatch) => {
    dispatch(requestCard());
    const url = `${Config.BASE_URL}${Config.CARD_SERVICE}/${cardId}`;
    fetchDelete({ url })
        .then((data) => dispatch(deleteCard({cardId})))
        .catch(() => dispatch(errorReceiveCard()));
}

export default {
    receiveCards,
    deleteCardById,
};