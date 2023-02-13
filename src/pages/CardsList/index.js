import React from "react";
import withAuthorities from "decorators/withAuthorities";
import { applyMiddleware, combineReducers, createStore } from "redux";
import CardsList from "./containers/CardsList";
import reducer from './reducers';
import thunkMiddleware from 'redux-thunk';
import { Provider } from "react-redux";


const rootReducer = combineReducers({
    reducer,
});
const store = createStore(
    rootReducer,
    applyMiddleware(thunkMiddleware),
);

export default withAuthorities(props => (
    <Provider store={store}>
        <CardsList {...props} />
    </Provider>
));