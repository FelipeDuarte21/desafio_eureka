import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useAuth from "../hooks/useAuth";
import Cabecalho from "../components/Cabecalho/Index";
import { buscarRoteiroPorId } from "../services/api";
import { votarRoteiro } from "../services/api";
import { mudarStatusRoteiro } from "../services/api";
import { Link } from "react-router-dom";

const StatusVoto = () => {

    const { id } = useParams();

    const { usuario } = useAuth();

    const [roteiro, setRoteiro ] = useState();

    const [decisao, setDecisao] = useState(true);
    const [justificativa, setJustificativa] = useState();

    const navigate = useNavigate();

    useEffect(() => {

        buscarRoteiroPorId(id, 
            response => {
                setRoteiro(response.data);
            },
            error => {
                console.log(error);
            }
        )

    }, []);

    const enviar = (event) => {

        event.preventDefault();

        let dados;

        if(usuario.cargo === 'Aprovador') {
            dados = {
                decisao: decisao,
                justificativa: justificativa
            }

            votarRoteiro(id, dados, 
                response => {
                    alert("Roteiro Votado!");
                    navigate('/home');
                },
                error => {
                    console.log(error);
                    alert("Erro ao Votar Roteiro");
                    navigate('/home');
                }
            );

        }else{

            dados = {
                avancaProcesso: decisao,
                justificativa: justificativa
            }

            mudarStatusRoteiro(id, dados, 
                response => {
                    alert("Status Alterado do Roteiro!");
                    navigate('/home');
                },
                error => {
                    console.log(error);
                    alert("Erro ao alterar Status do Roteiro");
                    navigate('/home');
                }
            );

        }

    }

    if(!roteiro) return (<></>);

    return (
        <>
            <Cabecalho />
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-10">

                        <h3 className="mt-4 text-center">Detalhe do Roteiro</h3>

                        <Link to={`/detalhe/${roteiro.id}`} className="btn btn-outline-secondary my-3">Voltar</Link>

                        <p className="my-4"><b>Status Atual: </b> {roteiro.statusAtual}</p>

                        <form onSubmit={e => enviar(e)} className="mt-4">

                            {(usuario.cargo === 'Aprovador' || (usuario.cargo === 'Analista' && roteiro.statusAtual === 'Em Análise'))  
                            && (
                                <>
                                    <label className="mb-0 form-label">Aprovado:</label>
                                    <select className="form-control" onChange={e => setDecisao(e.target.value)}>
                                        <option value="true">Sim</option>
                                        <option value="false">Não</option>
                                    </select>
                                </>
                            )}

                            <label className="mb-0 mt-3 form-label">Justificativa:</label>
                            <textarea className="form-control" cols="10" rows="10" onChange={e => setJustificativa(e.target.value)}></textarea>

                            <button type="submit" className="btn btn-primary mt-3">Enviar</button>

                        </form>

                    </div>
                </div>
            </div>
        </>
    )
}

export default StatusVoto;