import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext({});

export const AuthProvider = ({children}) => {

    const [usuario, setUsuario] = useState(null);
    const [isAutenticado, setIsAutenticado] = useState(false);

    const TOKEN = "token";
    const ID_USUARIO = "id_usuario";
    const CARGO = "cargo_usuario";
    const NOME = "nome_usuario";
    const EMAIL = "email_usuario";

    useEffect(() => {

        const token = localStorage.getItem(TOKEN);
        const idUsuario = localStorage.getItem(ID_USUARIO);
        const cargo = localStorage.getItem(CARGO);
        const nome = localStorage.getItem(NOME);
        const email = localStorage.getItem(EMAIL);

        if(token && idUsuario && cargo && nome && email) {
            let user = {
                token: token,
                id: idUsuario,
                nome: nome,
                cargo: cargo,
                email: email
            }
            setUsuario(user);
            setIsAutenticado(true);
        }

    }, []);

    const login = (token, idUsuario, nome, cargo, email) => {
        localStorage.setItem(TOKEN,token);
        localStorage.setItem(ID_USUARIO, idUsuario);
        localStorage.setItem(NOME, nome );
        localStorage.setItem(CARGO, cargo);
        localStorage.setItem(EMAIL, email);

        let user = {
            token: token,
            id: idUsuario,
            nome: nome,
            cargo: cargo,
            email: email
        }
        setUsuario(user);
        setIsAutenticado(true);
    }

    const logout = () => {
        localStorage.removeItem(TOKEN);
        localStorage.removeItem(ID_USUARIO);
        localStorage.removeItem(NOME);
        localStorage.removeItem(CARGO);
        localStorage.removeItem(EMAIL);
        setUsuario(null);
        setIsAutenticado(false);
        window.location.reload();
    }

    return <AuthContext.Provider value={{usuario, isAutenticado, login, logout}}>{children}</AuthContext.Provider>
};