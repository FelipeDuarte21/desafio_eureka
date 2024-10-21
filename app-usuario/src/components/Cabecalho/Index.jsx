import React from "react";
import useAuth from "../../hooks/useAuth";
import { Link, useNavigate } from "react-router-dom";


const Cabecalho = () => {

    const { usuario, logout } = useAuth();
    const navigate = useNavigate();

    const sair = () => {
        logout();
        navigate("/");
    }

    return (
        <nav className="navbar bg-primary text-light">
            <div className="container-fluid">
                <span className="navbar-brand text-light">Cooperfilme</span>
                <div className="d-flex justify-content-end align-itens-center">
                    <h5 className="me-2 mt-2">{usuario.nome} - {usuario.cargo}</h5>
                    <button className="btn btn-secondary" onClick={e => sair()}>Sair</button>
                </div>
            </div>
        </nav>
    )
}

export default Cabecalho;