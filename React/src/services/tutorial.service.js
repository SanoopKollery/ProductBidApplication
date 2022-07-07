import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/pq/e-auction/api/v1/seller/get-product");
  }

  get(id) {
    return http.get(`/tutorials/${id}`);
  }

  create(data) {
    return http.post("/pc/e-auction/api/v1/seller/add-product", data);
  }

  update(id, data) {
    return http.put(`/tutorials/${id}`, data);
  }

  delete(id) {
    return http.delete(`/tutorials/${id}`);
  }

  deleteAll() {
    return http.delete(`/tutorials`);
  }

  findByTitle(title) {
    return http.get(`/tutorials?title=${title}`);
  }

  saveBid(data){
    return http.post("/tc/e-auction/api/v1/buyer/place-bid", data);
  }

  getBidDetails(productID)
  {
    return http.get(`/tq/e-auction/api/v1/seller/show-bids/${productID}`);
  }
}

export default new TutorialDataService();