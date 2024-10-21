import React, { useEffect, useState} from "react";
import { Link, useParams } from "react-router-dom"
import { buscarRoteiroPorId } from "../services/api";
import Cabecalho from "../components/Cabecalho/Index";
import CardRoteiroDetalhe from "../components/CardRoteiroDetalhe";

const Detalhe = () => {

    const { id } = useParams();

    const [roteiro, setRoteiro ] = useState();

    useEffect(() => {

        buscarRoteiroPorId(id, 
            response => {
                setRoteiro(response.data);
            },
            error => {
                console.log(error);
            }
        )

    },[])

    return (
        <>
            <Cabecalho />
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-10">

                        <h3 className="mt-4 text-center">Detalhe do Roteiro</h3>

                        <Link to="/home" className="btn btn-outline-secondary my-3">Voltar</Link>

                        {roteiro && (
                            <CardRoteiroDetalhe roteiro={roteiro} />
                        )}

                    
                    </div>
                </div>
            </div>
        </>
    )
}

export default Detalhe;