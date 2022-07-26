import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import { Link } from "react-router-dom";
//import TransactionDetails from "./transaction-list-component";

export default class ProductList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchProductName = this.onChangeSearchProductName.bind(this);
    this.retrieveTutorials = this.retrieveTutorials.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTutorial = this.setActiveTutorial.bind(this);
    this.removeAllTutorials = this.removeAllTutorials.bind(this);
    this.searchProductName = this.searchProductName.bind(this);
    this.getBids = this.getBids.bind(this);
    this.state = {
      bids: [],
      tutorials: [],
      currentTutorial: null,
      currentIndex: -1,
      searchProductName: "", 
    };
    this.headers = [
			{ key: 'bidAmount', label: 'Bid Amount'},
			{ key: 'name', label: 'Name' },
			{ key: 'email', label: 'Email' },
      { key: 'phone', label: 'Phone' },
      { key: 'action', label: 'Action' }
		];
   
  }

  
  componentDidMount() {
    this.retrieveTutorials();
    this.getBids();
  }

  onChangeSearchProductName(e) {
    const searchProductName = e.target.value;

    this.setState({
      searchProductName: searchProductName
    });
  }
  retrieveTutorials() {
    TutorialDataService.getAll()
      .then(response => {
        this.setState({
          tutorials: response.data,
          transactions: response.data,
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveTutorials();
    this.getBids();
    this.setState({
      currentTutorial: null,
      currentIndex: -1,
    });
  }

  setActiveTutorial(tutorial, index) {
    
    this.setState({
      currentTutorial: tutorial,
      currentIndex: index,
    });
    
    TutorialDataService.getBidDetails(tutorial.productId)
    .then(response => {
      this.setState({ 
        bids:response.data,
      });
      console.log(response.data);
    })
    .catch(e => {
      console.log(e);
    });
  }

  removeAllTutorials() {
    TutorialDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }

  getBids(){
    TutorialDataService.getBidDetails("5116c6e9-7023-4cb2-b6a8-64a08f5691c41")
    .then(response => {
      this.setState({
        bids: response.data,
      });
      console.log(response.data);
    })
    .catch(e => {
      console.log(e);
    });
  }

  searchProductName() {
    this.setState({
      currentTutorial: null,
      currentIndex: -1
    });

    TutorialDataService.findByProductName(this.state.searchProductName)
      .then(response => {
        this.setState({
          tutorials: response.data,
        });
       // console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { searchProductName, tutorials, currentTutorial, currentIndex,bids} = this.state;
    
    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
          <input
              type="text"
              className="form-control"
              placeholder="Search by Product Name"
              value={searchProductName}
              onChange={this.onChangeSearchProductName}
            />
            <div className="input-group-append">
            <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchProductName}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <h4>Product List</h4>

          <ul className="list-group">
            {tutorials &&
              tutorials.map((tutorial, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveTutorial(tutorial, index)}
                  key={index}
                >
                  {tutorial.productName}
                </li>
              ))}
          </ul>

          <button
            className="m-3 btn btn-sm btn-danger"
            onClick={this.removeAllTutorials}
          >
            Remove All
          </button>
        </div>
        <div className="col-md-6">
          {currentTutorial ? (
            
            <div>
              <h4>Product Details</h4>
              <div>
                <label>
                  <strong>Product Name:</strong>
                </label>{" "}
                {currentTutorial.productName}
              </div>
              <div>
                <label>
                  <strong>Short Description:</strong>
                </label>{" "}
                {currentTutorial.shortDescription}
              </div>
              <div>
                <label>
                  <strong>Description:</strong>
                </label>{" "}
                {currentTutorial.description}
              </div>
              <div>
                <label>
                  <strong>Category:</strong>
                </label>{" "}
                {currentTutorial.category}
              </div>
              <div>
                <label>
                  <strong>Starting Price:</strong>
                </label>{" "}
                {currentTutorial.startingPrice}
              </div>
              <div>
                <label>
                  <strong>Bid End Date:</strong>
                </label>{" "}
                {currentTutorial.bidEndDate}
              </div>
              <Link
                to={"/tutorials/" + currentTutorial.productId}
                className="m-3 btn btn-sm btn-dark"
              >
                Create Bid
              </Link>
              <Link
                to={"/tutorials/" + currentTutorial.productId}
                className="m-3 btn btn-sm btn-danger"
              >
                Delete Product
              </Link>
            </div>
          ) : (
            <div>
              <br />
              <p>Please click on a Product...</p>
            </div>
          )}
        </div>
        
        <div>		
          <h5> Bid Details</h5>				
					<table class="divTable">
						<thead>
							<tr>
							{
								this.headers.map(function(h) {
									return (
										<th key = {h.key}>{h.label}</th>
									)
								})
							}
							</tr>
						</thead>
            
            {bids &&
              bids.map((item, index) => (
                <tbody>
                {
                   item.transactions.map((trxn, i) => (
                    <tr>
                      <td class="table-body-cell">{trxn.bidAmount}</td> 
                      <td class="table-body-cell">{trxn.firstName}</td> 
                      <td class="table-body-cell">{trxn.email}</td> 
                      <td class="table-body-cell">{trxn.phone}</td> 
                      <td><Link
                to={"/tutorials/" + currentTutorial.id}
                className="badge badge-warning"
              >
                UPDATE
              </Link></td>
                    </tr>
                    
                  ))
                }
                </tbody>
              
              ))}
						
					</table>
				</div>

      </div>
   
    );
  }
}

