import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1/roteiro'
});

axios.defaults.headers.post['Content-Type'] ='application/json';
axios.defaults.headers.post['Accept'] = 'application/json';

export const cadastrarRoteiro = (titulo, roteiro, nome, email, telefone, sucessoCallback, errorCallback) => {

    let roteiroTemplate = {
        titulo: titulo,
        texto: roteiro,
        cliente: {
            nome: nome,
            email: email,
            telefone: telefone
        } 
    }

    api.post('', roteiroTemplate)
    .then(response => sucessoCallback(response))
    .catch(error => errorCallback(error));

}

export const buscarRoteiros = async (nome, email, sucessoCallback, errorCallback, finallyCallback) => {

    try {

        const resposta = await api.get(`/cliente?nome=${nome}&email=${email}`);

        sucessoCallback(resposta);

    } catch (error) {
        errorCallback(error);
    } finally {
        finallyCallback();
    }

}