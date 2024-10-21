import React, { useState } from "react";
import { Link } from "react-router-dom";
import { buscarRoteiros } from "../services/api";
import FormularioBusca from "../components/FormularioBusca";
import CardRoteiro from '../components/CardRoteiro';
import Cabecalho from "../components/Cabecalho/Index";

const Consulta = () => {

    const [carregando, setCarregando] = useState(false);

    const [roteiros, setRoteiros] = useState([]);

    const consultar = async (nome, email) => {

        setCarregando(true);

        buscarRoteiros(nome, email,
            response => setRoteiros(response.data),
            error => console.log(error),
            () => setCarregando(false)
        );

    }

    if(carregando) return <p>Carregando.....</p>

    return (
        <>
            <Cabecalho />
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-10">

                        <h1 className="mt-3 text-center">Consulta de Status de Roteiro</h1>

                        <Link to="/" className="btn btn-outline-secondary">Voltar</Link>

                        <FormularioBusca onRetornar={consultar} />

                        {roteiros.length > 0 && (

                            <div className="mt-4">

                                <h4 className="text-center mb-3">Roteiros:</h4>

                                {roteiros.map((roteiro, index) => (

                                    <CardRoteiro key={index} roteiro={roteiro} />

                                ))}

                            </div>

                        )}
                    </div>

                </div>
            </div>
        </>
    )
}

export default Consulta;