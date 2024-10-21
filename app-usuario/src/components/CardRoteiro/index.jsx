import React from "react"
import { Link } from "react-router-dom";

const CardRoteiro = ({ roteiro }) => {
    return (
        <>
            <div className="card mb-4 px-3 py-4">

                <div className="row">

                    <div className="col-12 col-lg-2">
                        <h6 className="mb-0">Data:</h6>
                        <span>{roteiro.dataHoraEnvio}</span>
                    </div>
                    
                    <div className="col-12 col-lg-6 mt-3 mt-lg-0">
                        <h6 className="mb-0">Autor:</h6>
                        <span>{roteiro.cliente.nome}</span>
                    </div>

                    <div className="col-12 col-lg-2 mt-3 mt-lg-0">
                        <h6 className="mb-0">Status Atual:</h6>
                        <span>{roteiro.statusAtual}</span>
                    </div>
                    <div className="col-12 col-lg-1">
                        <Link to={`/detalhe/${roteiro.id}`} className="btn btn-primary mt-3 mt-lg-0">Visualizar</Link>
                    </div>
                </div>

            </div>
        </>
    )
}

export default CardRoteiro;