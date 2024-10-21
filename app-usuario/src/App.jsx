import React from "react"
import { BrowserRouter, Route, Routes } from "react-router-dom"
import Login from "./pages/Login"
import Home from "./pages/Home";
import { AuthProvider } from "./contexts/authContext";
import useAuth from "./hooks/useAuth";
import Detalhe from "./pages/Detalhe";
import StatusVoto from "./pages/StatusVoto";

function App() {

	const RotaProtegida = ({Item}) => {
		const {isAutenticado} = useAuth();
		return isAutenticado ? <Item/> : <Login /> ;
	}

	return (
		<AuthProvider>
			<BrowserRouter>
				<Routes>
					<Route exact path="/home" element={ <RotaProtegida Item={Home}/> } />
					<Route exact path="/detalhe/:id" element={ <RotaProtegida Item={Detalhe}/> } />
					<Route exact path="/statusVoto/:id" element={ <RotaProtegida Item={StatusVoto}/> } />
					<Route path="/" element={<Login />} />
				</Routes>
			</BrowserRouter>
		</AuthProvider>
	)
}

export default App
