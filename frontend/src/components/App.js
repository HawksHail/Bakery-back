import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "../styles/App.css";
import Navbar from "./Navbar";
import Home from "./Home";
import DisplayProducts from "./DisplayProducts";
import DisplayCart from "./DisplayCart";

function App() {
	return (
		<Router>
			<Navbar />
			<Switch>
				<Route exact path="/">
					<Home />
				</Route>
				<Route path="/products">
					<DisplayProducts />
				</Route>
				<Route path="/cart">
					<DisplayCart />
				</Route>
			</Switch>
		</Router>
	);
}

export default App;
