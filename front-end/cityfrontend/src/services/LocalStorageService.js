export default class LocalStorageService {

    /*
    LocalStorage
    Adequado para pequenas quantidades de dados que não
    precisam ser compartilhados entre diferentes abas ou janelas do navegador.
    */

    static adicionar_items(chave, valor){
        localStorage.setItem(chave, JSON.stringify(valor));
    }

    static obter_item(chave){
        const item = localStorage.getItem(chave);
        return JSON.parse(item)
    }

    static remover_item(chave){
        localStorage.removeItem(chave);
    }


}