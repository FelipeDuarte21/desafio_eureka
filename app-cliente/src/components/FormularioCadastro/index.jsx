import React, { useState } from "react";
import { cadastrarRoteiro } from "../../services/api";
import Campo from "../Campo";
import { useNavigate } from "react-router-dom";

const FormularioCadastro = () => {

    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [telefone, setTelefone] = useState('');
    const [titulo, setTitulo] = useState('');
    const [roteiro, setRoteiro] = useState('');

    const navigate = useNavigate();

    const enviarRoteiro = (event) => {

        event.preventDefault();

        cadastrarRoteiro(titulo, roteiro, nome,email, telefone, 
            response => { 
                console.log(response), 
                alert('Roteiro enviado com Sucesso');
                navigate('/consulta');
            },
            error => { 
                console.log(error), 
                alert("Erro ao enviar");
            }
        );
        
    }

    return (
        <>
            <form onSubmit={e => enviarRoteiro(e)}>

                <div className="row mb-3 mt-3">
                    <div className="col-12 col-md-4">
                        <Campo campo="nome" placeholder="digite seu nome" label="Seu Nome:" type="text" setValue={e => setNome(e.target.value)} />
                    </div>
                    <div className="col-12 col-md-4">
                        <Campo campo="email" placeholder="digite seu email" label="Seu Email:" type="email" setValue={e => setEmail(e.target.value)} />
                    </div>
                    <div className="col-12 col-md-4">
                        <Campo campo="telefone" placeholder="digite seu telefone" label="Seu Telefone:" type="text" setValue={e => setTelefone(e.target.value)} />
                    </div>
                </div>
                
                <Campo campo="titulo" placeholder="digite o titulo do seu roteiro" label="Título do Roteiro:" type="text" setValue={e => setTitulo(e.target.value)} />

                <label htmlFor="texto" className="mb-0 mt-3">Seu Roteiro:</label>
                <textarea id="texto" rows="10" cols="30" placeholder="digite seu roteiro" onChange={(e) => setRoteiro(e.target.value)} className="form-control"></textarea>

                <button type="submit" className="btn btn-primary mt-3 mb-4">Enviar</button>

            </form>
        </>
    )
}

export default FormularioCadastro;