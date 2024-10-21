import React from "react";
import { Link } from "react-router-dom";
import FormularioCadastro from "../components/FormularioCadastro";
import Cabecalho from "../components/Cabecalho/Index";

const Home = () => {

    return (
        <>
            <Cabecalho/>
            <div className="container ">
                <div className="row justify-content-center">
                    <div className="col-10">

                        <h1 className="text-center mt-3">Escreva Seu Roteiro</h1>

                        <p className="mt-4">Bem-vindo à plataforma da Cooperfilme! Estamos animados para receber sua história. 
                            Utilize o espaço abaixo para escrever seu roteiro diretamente. 
                            Nossa equipe de avaliadores está pronta para analisar sua proposta 
                            e oferecer feedback construtivo.
                        </p>

                        <h4 className="mt-3">Dicas para um roteiro eficaz:</h4>

                        <ul>
                            <li><b>Clareza:</b> Expresse suas ideias de forma simples e direta.</li>
                            <li><b>Estrutura:</b> Pense em uma introdução cativante, um desenvolvimento envolvente e uma conclusão impactante.</li>
                            <li><b>Público:</b> Considere como sua narrativa afetará a audiência.</li>
                        </ul>

                        <p>Cada roteiro é uma nova oportunidade de contar uma história única! Estamos ansiosos para ler o que você criou.</p>

                        <h4>Se você já escreveu seu roteiro, clique no link abaixo para saber sobre o andamento dele:</h4>

                        <Link to="/consulta" className="btn btn-outline-primary">Consultar Roteiros</Link>
                        
                        <h4 className="mt-4">Se não escreveu, comece a escrever seu roteiro aqui:</h4>

                        <FormularioCadastro/>

                    </div>

                </div>
            </div>
        </>
    )
}

export default Home;