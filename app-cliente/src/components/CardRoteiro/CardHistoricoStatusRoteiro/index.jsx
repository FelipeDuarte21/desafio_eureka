import React from "react";

const CardHistoricoStatusRoteiro = ({historicoStatus}) => {
    return (
        <>
            <h6 className="mt-3 text-center">Histórico:</h6>
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
                        {historicoStatus.map((registro,index) => (
                            <tr key={index}>
                                <td>{registro.dataHora}</td>
                                <td>{registro.status}</td>
                                <td>{registro.justificativa}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default CardHistoricoStatusRoteiro;