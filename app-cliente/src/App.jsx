import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from './pages/Home';
import Consulta from './pages/Consulta';

function App() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<Home />} />
				<Route path="/consulta" element={<Consulta />} />
			</Routes>
		</BrowserRouter>
	)
}

export default App
