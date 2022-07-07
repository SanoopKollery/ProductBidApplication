import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class Tutorial extends Component {
  constructor(props) {
    super(props);
    
    this.onChangeFname = this.onChangeFname.bind(this);
    this.onChangeLname = this.onChangeLname.bind(this);
    this.onChangeAddress = this.onChangeAddress.bind(this);
    this.onChangeCity = this.onChangeCity.bind(this);
    this.onChangeState = this.onChangeState.bind(this);
    this.onChangePhone = this.onChangePhone.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangePin = this.onChangePin.bind(this);
    this.onChangeBidAmount = this.onChangeBidAmount.bind(this);
    this.getTutorial = this.getTutorial.bind(this);
  
    this.state = {
      currentTutorial: {
        productId: this.props.match.params.id,
        description: "",
        published: false
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getTutorial(this.props.match.params.id);
  }

  onChangeFname(e) {
    const firstName = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          firstName: firstName
        }
      };
    });
  }

  onChangeLname(e) {
    const lastName = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          lastName: lastName
        }
      };
    });
  }

  onChangeAddress(e) {
    const address = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          address: address
        }
      };
    });
  }

  onChangeCity(e) {
    const city = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          city: city
        }
      };
    });
  }

  onChangeState(e) {
    const state = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          state: state
        }
      };
    });
  }

  onChangePin(e) {
    const pin = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          pin: pin
        }
      };
    });
  }

  onChangePhone(e) {
    const phone = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          phone: phone
        }
      };
    });
  }

  onChangeEmail(e) {
    const email = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          email: email
        }
      };
    });
  }

  onChangeBidAmount(e) {
    const bidamount = e.target.value;
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          bidamount: bidamount
        }
      };
    });
  }

  onChangeProductId(e) {
    const productId =this.props.match.params.id;
    
    this.setState(prevState => ({
      currentTutorial: {
        ...prevState.currentTutorial,
        productId: productId
      }
    }));
  }

  onChangeDescription(e) {
    const description = e.target.value;
    
    this.setState(prevState => ({
      currentTutorial: {
        ...prevState.currentTutorial,
        description: description
      }
    }));
  }

  getTutorial(id) {
    TutorialDataService.get(id)
      .then(response => {
        this.setState({
          currentTutorial: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updatePublished(status) {
    console.log(this.state.currentTutorial.fname);
    var data = {
      id: this.state.currentTutorial.productId,
      title: this.state.currentTutorial.title,
      description: this.state.currentTutorial.description,
      published: status
    };

    TutorialDataService.update(this.state.currentTutorial.productId, data)
      .then(response => {
        this.setState(prevState => ({
          currentTutorial: {
            ...prevState.currentTutorial,
            published: status
          }
        }));
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  saveTransaction() {
    var data = {
      firstName: this.state.currentTutorial.firstName,
      lastName:this.state.currentTutorial.lastName,
      address: this.state.currentTutorial.address,
      city: this.state.currentTutorial.city,
      state: this.state.currentTutorial.state,
      pin: this.state.currentTutorial.pin,
      phone: this.state.currentTutorial.phone,
      email: this.state.currentTutorial.email,
      bidAmount: this.state.currentTutorial.bidamount,
      productId:this.state.currentTutorial.productId
    };
    console.log(data);

    TutorialDataService.saveBid(
     data
    )
      .then(response => {
        console.log(response.data);
        this.setState({
          message: "Bid saved successfully!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  

  render() {
    const { currentTutorial } = this.state;

    return (
      <div>
        {currentTutorial ? (
          <div className="edit-form">
            <h4>Add Bid Details</h4>
            <form>
              <div className="form-group">
                <label htmlFor="firstName">First name</label>
                <input
                  type="text"
                  className="form-control"
                  id="firstName"
                  value={currentTutorial.firstName}
                  onChange={this.onChangeFname}
                />
                <label htmlFor="lastName">Last name</label>
                <input
                  type="text"
                  className="form-control"
                  id="lastName"
                  value={currentTutorial.lastName}
                  onChange={this.onChangeLname}
                />
                <label htmlFor="address">Address</label>
                <input
                  type="text"
                  className="form-control"
                  id="address"
                  value={currentTutorial.address}
                  onChange={this.onChangeAddress}
                />
                <label htmlFor="city">City</label>
                <input
                  type="text"
                  className="form-control"
                  id="city"
                  value={currentTutorial.city}
                  onChange={this.onChangeCity}
                />
                <label htmlFor="state">State</label>
                <input
                  type="text"
                  className="form-control"
                  id="state"
                  value={currentTutorial.state}
                  onChange={this.onChangeState}
                />
                <label htmlFor="pin">Pin</label>
                <input
                  type="text"
                  className="form-control"
                  id="pin"
                  value={currentTutorial.pin}
                  onChange={this.onChangePin}
                />
                <label htmlFor="phone">Phone</label>
                <input
                  type="text"
                  className="form-control"
                  id="phone"
                  value={currentTutorial.phone}
                  onChange={this.onChangePhone}
                />
                <label htmlFor="email">Email</label>
                <input
                  type="text"
                  className="form-control"
                  id="email"
                  value={currentTutorial.email}
                  onChange={this.onChangeEmail}
                />
                <label htmlFor="bidamout">Bid Amount</label>
                <input
                  type="text"
                  className="form-control"
                  id="bidamount"
                  value={currentTutorial.bidamount}
                  onChange={this.onChangeBidAmount}
                />
                <label htmlFor="productid" hidden>Product ID</label>
                <input
                  type="text"
                  className="form-control"
                  id="productid"
                  value={currentTutorial.productId}
                  onChange={this.onChangeProductId}
                  hidden
                />
              </div>
            </form>

            <button
              type="submit"
              className="badge badge-success"
              onClick={() => this.saveTransaction()}
            >
              SAVE Transaction
            </button>
            <p>{this.state.message}</p>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Tutorial...</p>
          </div>
        )}
      </div>
    );
  }
}
