import React from 'react'
import { useRoutes } from 'react-router-dom'

class LandingPage extends React.Component {

    goToHomePage = () => {
        this.props.history.push("/login")
    }

    render(){
        return (
            <div className="container text-center" >
                <h2>Bem vindo ao sistema CityGusa-Tech</h2>
                 <br/><br/>

                <div className="offset-md-4 col-md-4">
                    <button style={{ width: '100%' }}
                            onClick={this.goToHomePage}
                            className="btn btn-success">
                        <i className="pi pi-sign-in"></i> Acessar
                    </button>
                </div>
            </div>
        )
    }

}

export default useRoutes(LandingPage)