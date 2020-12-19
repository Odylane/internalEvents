import http from "../http-common";

class EventDataService {
    getAll() {
        return http.get("/events");
    }
}

export default new EventDataService();