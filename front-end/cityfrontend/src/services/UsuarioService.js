import ApiService from "./ApiService";
import ErroValidacao from "../exceptions/ErroValidacao";

export default class UsuarioService extends ApiService {

        constructor() {
            super('/users');
        }

        autenticar(credenciais){
        return this.post('/login', credenciais)
        }

        validar(usuario){

            const erros = []

            if(!usuario.nome){
                erros.push('O campo Nome é obrigatório.')
            }

            if(!usuario.senha){
                erros.push('Senha é obrigatório.')
            }

            if(erros && erros.length > 0){
                throw new ErroValidacao(erros);
            }
    }


}