import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import TextField from 'components/TextField';
import Button from "components/Button";
// import useAccessValidate from "hooks/useAccessValidate";

const EditCard = (
    { authorities }
) => {
    // const canSee = useAccessValidate({
    //     ownedAuthorities: authorities,
    //     neededAuthorities: ['МОЖНО_ВОТ_ЭТУ_ШТУКУ'],
    // });
    const { clientId } = useParams();

    useEffect(() => {
        console.log(`${clientId}`);
    }, []);

    const [state, setState] = useState({
        card: null,
    });

    return (
        <div>
            <TextField label='Code' />
            <br />
            <TextField type='number' label='CVV' />
            <br />
            <TextField label='Name' />
            <br />
            <TextField label='Surname' />
            <br /><br />
            <TextField type='date' />
            <br /><br />
            <Button variant="contained" size="big" color="primary">{clientId == 0 ? 'Create' : 'Update'}</Button>
        </div>
    );
}

export default EditCard;