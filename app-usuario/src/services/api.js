import axios from "axios";
import useAuth from "../hooks/useAuth";

const token = `Bearer ${localStorage.getItem("token")}`;

const api = axios.create({
    baseURL: 'http://localhost:8080'
});

export const fazerLogin = (email, senha, sucessoCallback, erroCallback) => {

    let login = {
        email: email,
        senha: senha
    }

    api.post('/api/v1/auth/login', login)
    .then(response => sucessoCallback(response))
    .catch(error => erroCallback(error));

}

export const buscarRoteiros = async (sucessoCallback, erroCallback) => {

    try{

        const resposta = await api.get("/api/v1/roteiro/usuario",
            { headers: { Authorization: token} });
        
        sucessoCallback(resposta.data);

    }catch(erro) {
        erroCallback(erro);
    }
    
}

export const buscarRoteiroPorId = async (id, successoCallback, erroCallback) => {

    try{

        const resposta = await api.get(`/api/v1/roteiro/${id}`, 
            { headers: { Authorization: token} });
        
        successoCallback(resposta);

    }catch(erro) {
        erroCallback(erro);
    }

}

export const mudarStatusRoteiro = async (id, dados, sucessoCallback, erroCallback) => {

    try{
        
        const resposta = api.put(`/api/v1/roteiro/${id}/status`,dados,
            { headers: { Authorization: token} });

        sucessoCallback(resposta);

    }catch(erro){
        erroCallback(erro);
    }

}

export const votarRoteiro = async (id, dados, sucessoCallback, erroCallback) => {

    try{
        
        const resposta = api.put(`/api/v1/roteiro/${id}/votacao`,dados,
            { headers: { Authorization: token} });

        sucessoCallback(resposta);

    }catch(erro){
        erroCallback(erro);
    }

}