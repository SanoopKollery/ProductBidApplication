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
      counter: -1
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

    TutorialDataService.get(e.target.value)
    .then(response => {
      this.setState({
        currentTutorial: response.data
      });
      console.log(response.data);
    })
    .catch(e => {
      console.log(e);
    });

    TutorialDataService.getBidDetails(e.target.value)
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
   
  }

  deleteWebsite(id) {
    console.log("hhhh"+id);
		if(window.confirm("Are you sure want to delete?")) {
			fetch('https://fsesquadeauction.azurewebsites.net/e-auction/api/v1/buyer/show-bid/'+ id)
				.then(response => {
          console.log(response);
					if(response.status === 200) {
            alert("Not able to delete !!, Transaction Exists !");
						window.location.href = '/';
					}
          else{
            TutorialDataService.delete(id);
						alert("Product deleted successfully");
						window.location.href = '/';
           
          }
			 });
		}
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
          <select className="form-control" value={searchProductName} onChange={this.onChangeSearchProductName}>
            <option>Select Product !</option>
            {tutorials.map((tutorial, index) => (
             
              <option value={tutorial.productId}>{tutorial.productName}</option>
            ))}
          </select>
          </div>
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
                to={"/createbid/" + currentTutorial.productId}
                className="m-3 btn btn-sm btn-dark"
              >
                Create Bid
              </Link>
              <button className="m-3 btn btn-sm btn-danger" onClick={this.deleteWebsite.bind(this, currentTutorial.productId)}>Delete Product</button>
            </div>
          ) : (
            <div>
              <br />
              <p>Please click on a Product...</p>
            </div>
          )}
        </div>
        <div className="col-md-6">
          
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
                { item.transactions &&
                   item.transactions.map((trxn, i) => (
                    <tr>
                      <td class="table-body-cell">{trxn.bidAmount}</td> 
                      <td class="table-body-cell">{trxn.firstName}</td> 
                      <td class="table-body-cell">{trxn.email}</td> 
                      <td class="table-body-cell">{trxn.phone}</td> 
                      <td><Link
                to={"/tutorials/" + trxn.transactionId}
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

