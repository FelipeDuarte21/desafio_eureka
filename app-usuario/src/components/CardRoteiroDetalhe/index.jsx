import React from "react"
import useAuth from "../../hooks/useAuth"
import { Link } from "react-router-dom";

const CardRoteiroDetalhe = ({ roteiro }) => {

    const { usuario } = useAuth();

    return (
        <>
            <div className="card mb-4 px-3 py-4">

                <div className="row">

                    <div className="col-12 col-lg-2">
                        <h6 className="mb-0">Data:</h6>
                        <span>{roteiro.dataHoraEnvio}</span>
                    </div>

                    <div className="col-12 col-lg-4 mt-3 mt-lg-0">
                        <h6 className="mb-0">Autor:</h6>
                        <span>{roteiro.cliente.nome}</span>
                    </div>

                    <div className="col-12 col-lg-3 mt-3 mt-lg-0">
                        <h6 className="mb-0">Email:</h6>
                        <span>{roteiro.cliente.email}</span>
                    </div>

                    <div className="col-12 col-lg-3 mt-3 mt-lg-0">
                        <h6 className="mb-0">Telefone:</h6>
                        <span>{roteiro.cliente.telefone}</span>
                    </div>

                </div>

                <div className="row mt-4">

                    <div className="col-12">
                        <h6 className="mb-0">Status Atual:</h6>
                        <span>{roteiro.statusAtual}</span>
                    </div>

                </div>

                <div className="row mt-4">
                    <div className="col">
                        <h6 className="mt-3">Histórico:</h6>
                        <div className="table-responsive">
                            <table className="table">
                                <thead>
                                    <tr className="table-light">
                                        <th>Data Hora</th>
                                        <th>Status</th>
                                        <th>Justificativa</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {roteiro.historicoStatus.map((registro, index) => (
                                        <tr key={index}>
                                            <td>{registro.dataHora}</td>
                                            <td>{registro.status}</td>
                                            <td>{registro.justificativa}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div className="row mt-4">

                    <div className="col-12">
                        <h6 className="mb-0">Título:</h6>
                        <span>{roteiro.titulo}</span>
                    </div>

                    <div className="col-12 mt-3">
                        <h6 className="mb-0">Roteiro:</h6>
                        <span>{roteiro.texto}</span>
                    </div>

                </div>

                <div className="row mt-4">
                    <div className="col-3">
                        {usuario.cargo != 'Aprovador' ?
                            <Link to={`/statusVoto/${roteiro.id}`} className="btn btn-primary">Mudar Status</Link>
                            : <Link to={`/statusVoto/${roteiro.id}`} type="button" className="btn btn-primary">Votar</Link>}
                    </div>
                </div>

            </div>
        </>
    )

}

export default CardRoteiroDetalhe