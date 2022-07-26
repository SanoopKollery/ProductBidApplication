import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/e-auction/api/v1/seller/get-product");
  }

  get(id) {
    return http.get(`/e-auction/api/v1/seller/get-product/${id}`);
  }

  create(data) {
    return http.post("/e-auction/api/v1/seller/add-product", data);
  }

  update(id, data) {
    return http.put(`/tutorials/${id}`, data);
  }

  delete(id) {
    return http.delete(`/e-auction/api/v1/seller/delete/${id}`);
  }

  deleteAll() {
    return http.delete(`/tutorials`);
  }

  findByProductName(productName) {
    return http.get(`/e-auction/api/v1/seller/get-product/name/${productName}`);
  }

  saveBid(data){
    return http.post("/e-auction/api/v1/buyer/place-bid", data);
  }

  getBidDetails(productID)
  {
    return http.get(`/e-auction/api/v1/seller/show-bids/${productID}`);
  }

  updateBid(productID,emailId,bidAmount)
  {
    return http.put(`e-auction/api/v1/buyer/update-bid/${productID}/${emailId}/${bidAmount}`);
  }

  getTransaction(transactionId)
  {
    return http.get(`/e-auction/api/v1/buyer/tutorials/${transactionId}`);
  }
}

export default new TutorialDataService();