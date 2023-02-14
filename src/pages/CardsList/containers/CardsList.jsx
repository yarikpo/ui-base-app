import useAccessValidate from "hooks/useAccessValidate";
import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import Actions from "../actions/card";
import Card from "components/Card";
import CardContent from "components/CardContent";
import Typography from "components/Typography";
import CardActions from "components/CardActions";
import Button from "components/Button";
import { makeStyles } from "@material-ui/core";
import { useHistory } from 'react-router-dom';
import * as PAGES from 'constants/pages';

const useStyles = makeStyles({
    root: {
        minWidth: 275,
        marginRight: 12,
        marginBottom: 20,
    },
    pos: {
        marginBottom: 12,
    },
    cards: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        width: 1000,
    },
    btn: {

    }
})

const CardsList = (
    { authorities }
) => {
    const dispatch = useDispatch();
    const canSee = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['МОЖНО_ВОТ_ЭТУ_ШТУКУ'],
    });
    const classes = useStyles();
    
    const {
        list,
        isLoading,
        isError,
    } = useSelector(({reducer}) => reducer.reducer);
    console.log({
        list,
        isLoading,
        isError,
    });

    useEffect(() => {
        dispatch(Actions.receiveCards());
    }, []);

    const deleteById = (cardId) => {
        dispatch(Actions.deleteCardById({cardId}));
    }

    const history = useHistory();
    const navigateToEdit = (id=null) => {
        history.push(`${PAGES.EDIT_CARD}/${id}`);
    }

    return (
        <div>
            {canSee && (
                <div className={classes.cards}>
                    <Button 
                        onClick={() => navigateToEdit(0)} 
                        size="small" 
                        color="primary"
                    >
                        Create
                    </Button>
                    {list.map(card => (
                        <div key={card.cardId}>
                            <Card className={classes.root}>
                                <CardContent>
                                    <Typography variant="h5" component="h2">
                                        {card.code}
                                    </Typography>
                                    <Typography 
                                        color="textSecondary" 
                                        gutterBottom
                                    >
                                        {card.cvv}
                                    </Typography>
                                    <Typography variant="body1" component="p">
                                        {card.surname} {card.name}
                                    </Typography>
                                    <Typography 
                                        className={classes.pos} 
                                        variant="body2" 
                                        component="p"
                                    >
                                        Date: {card.creationDate}
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button 
                                        onClick={() => navigateToEdit(card.cardId)} 
                                        size="small" 
                                        color="primary"
                                    >
                                        Edit
                                    </Button>
                                    <Button 
                                        onClick={() => deleteById(card.cardId)} 
                                        variant="contained" 
                                        color="secondary" 
                                        size="small"
                                        >
                                            Delete
                                        </Button>
                                </CardActions>
                            </Card>
                        </div>
                    ))}
                </div>
            )}
            {!canSee && (
                <div>
                    No Access.
                </div>
            )}
            
        </div>
    );
}

export default CardsList;