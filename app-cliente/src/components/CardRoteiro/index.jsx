import React from "react"
import CardHistoricoStatusRoteiro from "./CardHistoricoStatusRoteiro";

const CardRoteiro = ({ roteiro }) => {
    return (
        <>
            <div className="card mb-4 px-3 py-4">

                <div className="row">

                    <div className="col-12 col-md-4">
                        <h6 className="mb-0">Data:</h6>
                        <span>{roteiro.dataHoraEnvio}</span>
                    </div>

                    <div className="col-12 col-md-4">
                        <h6 className="mb-0">Titulo:</h6>
                        <span>{roteiro.titulo}</span>
                    </div>
                    <div className="col-12 col-md-4 text-md-end">
                        <h6 className="mb-0 mt-2">Status Atual:</h6>
                        <span className={roteiro.statusAtual === 'Aprovado' 
                            ? 'text-success' : roteiro.statusAtual === 'Recusado' 
                                ? 'text-danger' : ''}>{roteiro.statusAtual}</span>
                    </div>
                </div>

                <CardHistoricoStatusRoteiro historicoStatus={roteiro.historicoStatus} />

                <h6 className="mt-3 mb-0">Roteiro:</h6>
                <p>{roteiro.texto}</p>
                
            </div>
        </>
    )
}

export default CardRoteiro;