import LocalStorageService from "./LocalStorageService";
import jwt from "jsonwebtoken";
import ApiService from "./ApiService";


export const TOKEN = 'access_token'
export const USUARIO_LOGADO = '_usuario_logado'

export default class AuthService{


    static isUsuarioAuthenticado(){
        //get token
        const token = LocalStorageService.obter_item(TOKEN)
        if (!token){
            return false
        }
        //get decoded token
        const token_decoded = jwt.decode(token)
        const expiration = token_decoded.exp

        //Compara o timestamp atual com o tempo de expiração multiplicado por 1000 para convertê-lo em milissegundos.
        //Se o timestamp atual for maior ou igual ao tempo de expiração, significa que o token expirou e a condição será verdadeira.
        const e_token_invalido = Date.now() >= (expiration * 1000)
        return !e_token_invalido;
    }

    static remover_usuario_autenticado(){
        LocalStorageService.remover_item(USUARIO_LOGADO);
        LocalStorageService.remover_item(TOKEN);
    }

    static logar(usuario, token){
        LocalStorageService.adicionar_items(USUARIO_LOGADO, usuario);
        LocalStorageService.adicionar_items(TOKEN, token);
        ApiService.registrarToken(token);
    }

    static obterUsuarioLogado(){
        return LocalStorageService.obter_item(USUARIO_LOGADO);
    }

    static refreshSession(){
        const token = LocalStorageService.obter_item(TOKEN);
        const usuario = AuthService.obterUsuarioLogado();
        AuthService.logar(usuario, token);
        return usuario;
    }



}