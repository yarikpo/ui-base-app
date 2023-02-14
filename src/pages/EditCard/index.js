import { combineReducers, createStore, applyMiddleware } from "redux";
import EditCard from "./containers/EditCard";
import reducer from './reducers';
import thunkMiddleware from 'redux-thunk';
import withAuthorities from "decorators/withAuthorities";
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
        <EditCard {...props} />
    </Provider>
));