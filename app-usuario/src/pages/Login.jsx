import React, {useState} from "react";
import { fazerLogin } from '../services/api';
import useAuth from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";

const Login = () => {

    const { login } = useAuth();
    const navigate = useNavigate();

    const [email,setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [erro, setErro] = useState(false);

    const entrar = (event) => {

        event.preventDefault();

        fazerLogin(email,senha, 
            async response => {
                let usu = response.data;
                login(usu.token, usu.idUsuario, usu.nome, usu.cargo, usu.email);
                setErro(false);
                navigate("/home");
            },
            error => {
                setErro(true);
                console.log(error);
            }
        );

    }

    return (
        <>
            <div className="container vh-100 d-flex justify-content-center align-items-center">

                <div className="row">

                    <div className="col">

                        <div className="card px-4">

                            <h5 className="text-center mt-4">Dasboard - Cooperfilme</h5>
                            <h6 className="text-center">Bem-vindo</h6>

                            <form onSubmit={e => entrar(e)}> 

                                <label htmlFor="email" className="mb-0 mt-3">Email:</label>
                                <input type="email" id="email" className="form-control" placeholder="digite seu email" onChange={e => setEmail(e.target.value)} />

                                <label htmlFor="senha" className="mb-0 mt-3">Senha:</label>
                                <input type="password" id="senha" className="form-control" placeholder="digite sua senha" onChange={e => setSenha(e.target.value)} />
                                
                                {erro && (
                                    <small className="d-block text-center text-danger mt-3">Email ou Senha Incorretos!</small>
                                )}

                                <button type="submit" className="btn btn-primary mt-3 mb-4">Entrar</button>
                            
                            </form>

                        </div>
                    
                    </div>

                </div>

            </div>

        </>
    )
}

export default Login;