import axios from 'axios';

export const httClient = axios.create(
    {baseUrl:baseUrl, withCredentials:true}
);

class ApiService{
    constructor(apiurl) {
        this.apiurl = apiurl;
    }

    /*
    * Esta função serve para configurar o cabeçalho de autorização das requisições HTTP realizadas
    *  usando o httpClient. Ela verifica se o token de autenticação foi fornecido e,se positivo,
    * o adiciona ao cabeçalho no formato correto (Bearer ${token}).
    * Isso garante que as requisições subsequentes sejam enviadas com a informação de autenticação necessária.
    * */
    static registrarToken(token){
        if (token){
            httClient.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        }
    }

    /*Esta função serve para realizar uma requisição POST para um endpoint da API. Ela recebe a URL relativa
     e os dados a serem enviados, constrói a URL completa e a envia usando o httpClient. Por ser uma promiss,
     permite tratar o resultado da requisição de forma assíncrona.*/
     post(url, objeto){
        const requestUrl = `${this.apiurl}${url}`;
        return httClient.post(requestUrl, objeto);
    }

        /*Esta função realiza uma requisição PUT a um endpoint da API.
    Recebe a URL relativa e os dados, constrói a URL completa e a envia usando o httpClient.
    O uso da promiss permite o tratamento assíncrono do resultado da requisição.
    */
    put(url, objeto){
        const requestUrl = `${this.apiurl}${url}`;
        return httClient.put(requestUrl, objeto);
    }

    delete(url, objeto){
        const requestUrl = `${this.apiurl}${url}`;
        return httClient.delete(requestUrl, objeto);
    }

}

export default ApiService;

