import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddProduct from "./components/add-product.component";
import Product from "./components/tutorial.component";
//import TransactionList from "./components/tutorials-list.component";
import ProductList from "./components/product-list.componet";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            FSE SQUAD
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/"} className="nav-link">
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/pc/e-auction/api/v1/seller/add-product"} className="nav-link">
                Add Product
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/pq/e-auction/api/v1/seller/get-product"]} component={ProductList} />
            <Route exact path="/pc/e-auction/api/v1/seller/add-product" component={AddProduct} />
            <Route path="/tutorials/:id" component={Product} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
