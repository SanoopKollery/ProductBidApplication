import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class AddProduct extends Component {
  constructor(props) {
    super(props);
    this.onChangeProductName = this.onChangeProductName.bind(this);
    this.onChangeShortDescription = this.onChangeShortDescription.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.onChangeCategory = this.onChangeCategory.bind(this);
    this.onChangeStartingPrice = this.onChangeStartingPrice.bind(this);
    this.onChangeBidEndDate = this.onChangeBidEndDate.bind(this);
    this.saveTutorial = this.saveTutorial.bind(this);
    this.newTutorial = this.newTutorial.bind(this);

    this.state = {
      productId: null,
      productName: "",
      shortDescription:"",
      description: "", 
      category:"",
      startingPrice:"",
      bidEndDate:"",
      saved:false
      //published: false,

      //submitted: false
    };
  }

  onChangeProductName(e) {
    this.setState({
      productName: e.target.value
    });
  }

  onChangeShortDescription(e) {
    this.setState({
      shortDescription: e.target.value
    });
  }

  onChangeDescription(e) {
    this.setState({
      description: e.target.value
    });
  }

  onChangeCategory(e) {
    this.setState({
      category: e.target.value
    });
  }

  onChangeStartingPrice(e) {
    this.setState({
      startingPrice: e.target.value
    });
  }

  onChangeBidEndDate(e) {
    this.setState({
      bidEndDate: e.target.value
    });
  }

  saveTutorial() {
    var data = {
      productName: this.state.productName,
      shortDescription:this.state.shortDescription,
      description: this.state.description,
      category: this.state.category,
      startingPrice: this.state.startingPrice,
      bidEndDate: this.state.bidEndDate
    };

    TutorialDataService.create(data)
      .then(response => {
        this.setState({
          productId: response.data.productId,
          productName: response.data.productName,
          shortDescription:response.shortDescription,
          description: response.data.description,
          category:response.data.onChangeCategory,
          startingPrice: response.data.startingPrice,
          bidEndDate:response.data.bidEndDate,
          //published: response.data.published,
          saved:response.data.saved
          //submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newTutorial() {
    this.setState({
      productId: null,
      productName: "",
      shortDescription:"",
      description: "",
      category:"",
      startingPrice:"",
      bidEndDate:"",
      saved:false
      //published: false,

      //submitted: false
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.saved ? (
          <div>
            <h4>Product saved successfully!</h4>
            <button className="btn btn-success" onClick={this.newTutorial}>
              Add Product
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="productName">Product Name</label>
              <input
                type="text"
                className="form-control"
                id="productName"
                required
                value={this.state.productName}
                onChange={this.onChangeProductName}
                name="productName"
              />
              <label htmlFor="shortDescription">Short Description</label>
              <input
                type="text"
                className="form-control"
                id="shortDescription"
                required
                value={this.state.shortDescription}
                onChange={this.onChangeShortDescription}
                name="shortDescription"
              />
              <label htmlFor="description">Description</label>
              <input
                type="text"
                className="form-control"
                id="description"
                required
                value={this.state.description}
                onChange={this.onChangeDescription}
                name="description"
              />
              <label htmlFor="description">Category</label>
              <input
                type="text"
                className="form-control"
                id="category"
                required
                value={this.state.category}
                onChange={this.onChangeCategory}
                name="category"
              />
              <label htmlFor="startingPrice">Starting Price</label>
              <input
                type="amount"
                className="form-control"
                id="startingPrice"
                required
                value={this.state.startingPrice}
                onChange={this.onChangeStartingPrice}
                name="startingPrice"
              />
              <label htmlFor="bidDate">Bid Date</label>
              <input
                type="date"
                className="form-control"
                id="bidDate"
                required
                value={this.state.bidDate}
                onChange={this.onChangeBidEndDate}
                name="bidDate"
              />
            </div>



            <button onClick={this.saveTutorial} className="btn btn-success">
              Submit
            </button>
          </div>
        )}
      </div>
    );
  }
}
