import jwt from "jsonwebtoken";
import AuthService from "../services/AuthService";
import React from "react";

/*
AuthContext que permite compartilhar dados relacionados à autenticação
 (como status de login, informações do usuário etc.)
 */
export const AuthContext = React.createContext();

/*AuthConsume é uma forma de os componentes se conectarem ao AuthContext e obterem suas informações de autenticação.*/
export const AuthConsumer = AuthContext.Consumer;

/*
* permite fornecer os dados do contexto para todos os componentes filhos
* */
const AuthProvider = AuthContext.Provider;

class ProvedorAuthenticacao extends React.Component{

    state = {
        usuarioAutenticado: null,
        isAutenticado: false,
        isLoading: true
    }

    iniciarSessao = (tokenDTO) => {
        const token = tokenDTO.token
        const claims = jwt.decode(token)
        const usuario = {
            id: claims.userid,
            nome: claims.nome
        }

        AuthService.logar(usuario, token);
        this.setState({ isAutenticado: true, usuarioAutenticado: usuario })
    }

    encerrarSessao = () => {
        AuthService.removerUsuarioAutenticado();
        this.setState({ isAutenticado: false, usuarioAutenticado: null})
    }

    async componentDidMount(){
        const isAutenticado = AuthService.isUsuarioAutenticado()
        if(isAutenticado){
            const usuario = await AuthService.refreshSession()
            this.setState({
                isAutenticado: true,
                usuarioAutenticado: usuario,
                isLoading: false
            })
        }else{
            this.setState( previousState => {
                return {
                    ...previousState,
                    isLoading: false
                }
            })
        }
    }

    render(){

        if(this.state.isLoading){
            return null;
        }

        const contexto = {
            usuarioAutenticado: this.state.usuarioAutenticado,
            isAutenticado: this.state.isAutenticado,
            iniciarSessao: this.iniciarSessao,
            encerrarSessao: this.encerrarSessao
        }

        return(
            <AuthProvider value={contexto} >
                {this.props.children}
            </AuthProvider>
        )
    }
}

export default ProvedorAutenticacao;