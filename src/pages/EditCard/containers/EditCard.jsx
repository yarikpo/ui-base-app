import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import TextField from 'components/TextField';
import Button from "components/Button";
import useAccessValidate from "hooks/useAccessValidate";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import Actions from "../actions/card";
import * as PAGES from 'constants/pages';

const EditCard = (
    { authorities }
) => {
    const dispatch = useDispatch();
    const canSee = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['МОЖНО_ВОТ_ЭТУ_ШТУКУ'],
    });
    const { cardId } = useParams();
    const {
        card,
        isError,
        isLoading,
        isRedirect,
    } = useSelector(({reducer}) => reducer.reducer);
    console.log({
        card,
        isError,
        isLoading,
        isRedirect,
    });

    const history = useHistory();
    const navigateToCardList = () => {
        history.push(`/${PAGES.CARDS_LIST}`)
    }

    const handleUpdate = () => {
        if (cardId > 0) {
            dispatch(Actions.updateCardById({
                cardId,
                card: state.card,
            }));
        }
        else {
            dispatch(Actions.saveNewCard({
                card: state.card,
            }))
        }
        navigateToCardList();
    }

    const handleCodeChange = (e) => {
        console.log(e.target.value);
        setState(prev => ({
            ...prev,
            card: {
                ...state.card,
                code: e.target.value,
            },
        }));
    }

    const handleCVVChange = (e) => {
        console.log(e.target.value);
        setState(prev => ({
            ...prev,
            card: {
                ...state.card,
                cvv: e.target.value,
            },
        }));
    }

    const handleNameChange = (e) => {
        console.log(e.target.value);
        setState(prev => ({
            ...prev,
            card: {
                ...state.card,
                name: e.target.value,
            },
        }));
    }

    const handleSurnameChange = (e) => {
        console.log(e.target.value);
        setState(prev => ({
            ...prev,
            card: {
                ...state.card,
                surname: e.target.value,
            },
        }));
    }

    const handleDateChange = (e) => {
        console.log(e.target.value);
        setState(prev => ({
            ...prev,
            card: {
                ...state.card,
                creationDate: e.target.value,
            },
        }));
    }

    useEffect(() => {
        console.log(`${cardId}`);
        
        if (cardId > 0) {
            dispatch(Actions.receiveCardById({ cardId }));
        }
    }, []);

    useEffect(() => {
        setState(prev => ({
            ...prev,
            card,
        }));
    }, [card]);

    const [state, setState] = useState({card});
    console.log(state);

    return (
        <div>
            {canSee && !isLoading && (
                // defaultValue={card.code || ''}
                <>
                    <TextField 
                        onChange={handleCodeChange} 
                        defaultValue={
                            card != null && cardId > 0 ? card.code : ''
                        } 
                        label='Code' 
                    />
                    <br />
                    <TextField 
                        onChange={handleCVVChange} 
                        defaultValue={
                            card != null && cardId > 0 ? card.cvv : ''
                        } 
                        type='number' 
                        label='CVV' 
                    />
                    <br />
                    <TextField 
                        onChange={handleNameChange} 
                        defaultValue={
                            card != null && cardId > 0 ? card.name : ''
                        } 
                        label='Name' 
                    />
                    <br />
                    <TextField 
                        onChange={handleSurnameChange} 
                        defaultValue={
                            card != null && cardId > 0 ? card.surname : ''
                        } 
                        label='Surname' 
                    />
                    <br /><br />
                    <TextField 
                        onChange={handleDateChange} 
                        defaultValue={
                            card != null && cardId > 0 ? card.creationDate : ''
                        } 
                        type='date' 
                    />
                    <br /><br />
                    <Button 
                        onClick={handleUpdate} 
                        variant="contained" 
                        size="big" 
                        color="primary">{cardId == 0 ? 'Create' : 'Update'}
                    </Button>
                    <Button 
                        onClick={navigateToCardList} 
                        size="big" 
                        color="primary"
                    >
                        Cancel
                    </Button>
                </>
            )}
        </div>
    );
}

export default EditCard;