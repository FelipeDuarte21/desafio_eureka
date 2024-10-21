import React, {useState} from "react";
import Campo from '../Campo';

const FormularioBusca = ({onRetornar}) => {

    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');

    const submit = async (event) => {
        event.preventDefault();
        onRetornar(nome,email);
    }

    return (
        <>
            <form onSubmit={e => submit(e)}>

                <div className="row mt-3">
                    <div className="col-12 col-md-6">
                        <Campo campo="nome" placeholder="digite seu nome" label="Seu Nome:" type="text" setValue={e => setNome(e.target.value)} />
                    </div>
                    <div className="col-12 col-md-4 mt-2 mt-md-0">
                        <Campo campo="email" placeholder="digite seu email" label="Seu Email:" type="email" setValue={e => setEmail(e.target.value)} />
                    </div>
                    <div className="col-12 col-md-2 mt-2 mt-md-0 d-flex align-items-end">
                        <button type="submit" className="btn btn-primary">Consultar</button>
                    </div>
                </div>

                

            </form>
        </>
    )
}

export default FormularioBusca;