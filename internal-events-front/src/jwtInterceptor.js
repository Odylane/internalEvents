import axios from "axios";
import store from "@/store";

const jwtInterceptor = axios.create({});

jwtInterceptor.interceptors.request.use((config) => {
  const authData = store.state.auth.authData;
    config.headers.common["Authorization"] = "Bearer " + authData.token;
    return config;
    });
export default jwtInterceptor;
