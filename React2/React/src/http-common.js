import axios from "axios";

export default axios.create({
  baseURL: "https://fsesquadeauction.azurewebsites.net",
  headers: {
    "Content-type": "application/json"
  }
});