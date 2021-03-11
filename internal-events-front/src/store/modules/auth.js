import axios from "axios";
import jwtInterceptor from "@/jwtInterceptor";

const state = {
  authData: {
    token: "",
    refreshToken: "",
    userId: "",
  },
  loginStatus: "",
  currentEmployee:{},
};

const getters = {
  getLoginStatus(state) {
    return state.loginStatus;
  },
};

const mutations = {
  SAVE_TOKEN_DATA(state, data) {
    localStorage.setItem("access_token", data.access_token);
    localStorage.setItem("refresh_token", data.refresh_token);
    localStorage.setItem("userId", data.userId);

    const newTokenData = {
      token: data.access_token,
      refreshToken: data.refresh_token,
      userId: data.userId,
    };

    state.authData = newTokenData;
  },
  SET_LOGIN_STATUS(state, value) {
    state.loginStatus = value;
  },
  SET_CURRENT_EMPLOYEE(state, currentEmployee) {
    // object -> string
    localStorage.currentEmployee = JSON.stringify(currentEmployee);
    state.currentEmployee = currentEmployee;
  },
  REMOVE_TOKEN_DATA(state) {
    localStorage.removeItem("access_token");
    localStorage.removeItem("refresh_token");
    state.authData.token = "";
    state.authData.refreshToken = "";
    state.authData.userId = "";
  },
};

const actions = {
  async login({ commit, dispatch }, payload) {
    console.log(payload);
    const response = await axios
      .post("http://localhost:8085/oauth/token", payload, {
        "Content-Type": "application/x-www-form-urlencoded",
      })
      .catch((error) => {
        console.log(error);
      });
    console.log(response);
    if (response && response.data) {
      commit("SAVE_TOKEN_DATA", response.data);
      commit("SET_LOGIN_STATUS", "success");
      dispatch("getCurrentEmployee");
    } else {
      commit("SET_LOGIN_STATUS", "failed");
    }
  },
  getCurrentEmployee({ commit }) {
    jwtInterceptor
      .get("http://localhost:8085/api/userInfo")
      .then(({ data }) => commit("SET_CURRENT_EMPLOYEE", data))
      .catch((error) => console.log(error));
  },
  logout({ commit }) {
    commit('REMOVE_TOKEN_DATA')
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
