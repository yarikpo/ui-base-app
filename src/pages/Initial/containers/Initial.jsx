import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useSelector } from 'react-redux';
import Link from 'components/Link';
import Typography from 'components/Typography';
import useAccessValidate from 'hooks/useAccessValidate';
import Button from 'components/Button';
import { useHistory } from 'react-router-dom';
import * as PAGES from 'constants/pages';

const getClasses = makeStyles(() => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
  },
}));

const Initial = ({
  authorities,
}) => {
  const classes = getClasses();
  const history = useHistory();
  const navigateToCardList = () => {
    history.push(`/${PAGES.CARDS_LIST}`)
  }
  const {
    availableItems,
  } = useSelector(({ reducer })=> reducer);
  const canSeeList = useAccessValidate({
    ownedAuthorities: authorities,
    neededAuthorities: ['МОЖНО_ВОТ_ЭТУ_ШТУКУ'],
  });

  return (
    <div className={classes.container}>
      {canSeeList && availableItems.map((item, index) => (
        <Link
          href={index % 2 === 0
            ? `https://www.google.com.ua/search?q=${item}&hl=ru`
            : undefined}
          to={index % 2 !== 0
            ? (location => ({
              ...location,
              pathname: `/${item}`,
              search: `${location.search}&newProp=42`,
            }))
            : undefined}
        >
          <Typography>
            {item}
          </Typography>
        </Link>
      ))}
      {canSeeList && (
        <Button 
          onClick={navigateToCardList} 
          size="big" 
          color="primary"
        >
          List
        </Button>
      )}
      {!canSeeList && (
        <Typography>
          Не могу ничего показать :(
        </Typography>
      )}
    </div>
  )
};

export default Initial;
