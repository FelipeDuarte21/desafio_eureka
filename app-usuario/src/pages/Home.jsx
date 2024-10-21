import React, { useEffect, useState } from "react"
import { buscarRoteiros } from "../services/api";
import CardRoteiro from "../components/CardRoteiro";
import useAuth from "../hooks/useAuth";
import Cabecalho from "../components/Cabecalho/Index";

const Home = () => {

    const [roteiros, setRoteiros] = useState(null);
    const [roteirosOriginal, setRoteirosOriginal] = useState(null);
    const { usuario } = useAuth();

    useEffect(() => {

        if(usuario) {
            buscarRoteiros(
                dados => {
                    setRoteiros(dados);
                    setRoteirosOriginal(dados);
                },
                error => {
                    console.log(error);
                }
            );
        }

    }, []);

    const filtro = (status) => {
        if(status === 'tudo') {
            setRoteiros(roteirosOriginal);
            return;
        }
        const filtrados = roteirosOriginal.filter(roteiro => roteiro.statusAtual.toLowerCase() === status.toLowerCase());
        setRoteiros(filtrados);
    }

    return (
        <>
            <Cabecalho />
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-10">

                        <h3 className="mt-4 text-center">Roteiros</h3>

                        <div className="mt-3 mb-4">
                            <span className="me-3">Filtros:</span>
                            <button className="btn btn-outline-secondary me-2" onClick={e => filtro('tudo')}>Tudo</button>
                            <button className="btn btn-outline-secondary me-2" onClick={e => filtro('Aguardando Análise')}>Aguardando Análise</button>
                            {usuario.cargo === 'Analista' ? <button className="btn btn-outline-secondary me-2" onClick={e => filtro('Em Análise')}>Em Análise</button> : <></>}
                            {usuario.cargo === 'Revisor' ? <button className="btn btn-outline-secondary me-2" onClick={e => filtro('Aguardando Revisão')}>Aguardando Revisão</button> : <></>}
                            {usuario.cargo === 'Revisor' ? <button className="btn btn-outline-secondary me-2" onClick={e => filtro('Em Revisão')}>Em Revisão</button> : <></>}
                            {usuario.cargo === 'Aprovador' ? <button className="btn btn-outline-secondary me-2" onClick={e => filtro('Aguardando Aprovação')}>Aguardando Aprovação</button> : <></>}
                            {usuario.cargo === 'Aprovador' ? <button className="btn btn-outline-secondary me-2" onClick={e => filtro('Em Aprovação')}>Em Aprovação</button> : <></>}
                        </div>

                        {roteiros && (
                            <>

                                {roteiros.length == 0 && (
                                    <div className="alert alert-warning mt-3">
                                        <span className="d-block text-center">Nenhum Roteiro Por Enquanto!!</span>
                                    </div>
                                )}

                                {roteiros.length > 0 && (
                                    <>
                                        {roteiros.map((roteiro, index) => (
                                            <CardRoteiro key={index} roteiro={roteiro} />
                                        ))}
                                    </>
                                )}

                            </>
                        )}

                    </div>
                </div>
            </div>
        </>
    )
}

export default Home