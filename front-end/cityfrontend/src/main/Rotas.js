import { Route, Switch, BrowserRouter, Redirect } from 'react-router-dom'
import LandingPage from "../views/LandingPage";
import Login from "../views/Login";
import Home from "../views/Home";
import React from "react";
import { AuthConsumer } from "./provedorAuthenticacao";

function RotaAutenticada({component: Component, isUsuarioAutenticado, ...props}){
    return (
        <Route exact {...props} render={(component) => {
            if(isUsuarioAutenticado){
                return (
                    <Component{...props}/>
                )
            }else{
                return (
                    <Redirect to={ {pathname : '/login', state : { from: componentProps.location } } } />
                )
            }
        }}/>
    )
}

function Rotas(props){
    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/" component={LandingPage}></Route>
                <Route exact path="/login" component={Login}></Route>

                <RotaAutenticada isUsuarioAutenticado={props.isUsuarioAuthenticado} path="/home" component={Home}></RotaAutenticada>
            </Switch>
        </BrowserRouter>
    )
}
export default () => (
    <AuthConsumer>
        { (context) => (<Rotas isUsuarioAutenticado={context.isAutenticado} />) }
    </AuthConsumer>
)