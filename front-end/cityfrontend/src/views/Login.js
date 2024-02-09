import logo from "../images/logo.png";
import {Component} from "react";
import UsuarioService from '../services/UsuarioService';
import {mensagemErro} from "../components/toastr";


class Login extends Component {

    entrar = () => {
        this.service.autenticar({nome: this.state.email, senha: this.state.senha}).then(
            response =>{
                this.context.iniciarSessao(response.data)
                this.props.history.push('/home')
            }).catch(erro => {mensagemErro(erro.response.data)})

    }

    state= {
        email: "",
        senha: ""
    }


    constructor() {
        super();
        this.service = new UsuarioService();
    }

    render() {
        return (<div className="container-fluid p-0 backgroundImg">
                <header className="">
                    <section className="gradient-form">
                        <div className="container h-100">
                            <div className="row d-flex justify-content-center align-items-center h-100">
                                <div className="col-xl-5">
                                    <div className="card rounded-4 text-black">
                                        <div className="row">
                                            <div className="">
                                                <div className="card-body p-4 mx-md-5">
                                                    <div className="text-center">
                                                        <img className="logo-tamanho" src={logo} alt="logo"/>
                                                    </div>
                                                    <form>
                                                        <p className={"text-center mt-5"}>Faça login com sua conta</p>

                                                        <div className="form-outline my-4">
                                                            <label className="form-label"
                                                                   htmlFor="form2Example11">Usuário</label>
                                                            <input type="text" id="nome" name="nome"
                                                                   className="form-control"
                                                                   placeholder="usuario corporativo"/>

                                                        </div>
                                                        <div className="form-outline">
                                                            <label className="form-label"
                                                                   htmlFor="">Senha</label>
                                                            <input type="password" id="senha" name="senha" placeholder="***********"
                                                                   className="form-control"/>

                                                        </div>
                                                        <div className="text-center pt-1 my-5 pb-1">
                                                            <button
                                                                className="btn btn-warning"
                                                                type="button">Entrar
                                                            </button>
                                                        </div>
                                                        <div
                                                            className="d-flex align-items-center justify-content-center pb-2">
                                                            <h2 className="">CITYGUSA <b className="tech">TECH</b></h2>
                                                        </div>

                                                    </form>

                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </header>

            </div>);
    }
}


export default Login;