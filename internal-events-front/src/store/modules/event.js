import jwtInterceptor from "@/jwtInterceptor";

const state = {
  events: [],
};

const mutations = {
  SET_EVENTS(state, events) {
    state.events = events;
  },
};

const actions = {
  getEvents({ commit }) {
    jwtInterceptor
      .get("http://localhost:8085/api/events")
      .then(({ data }) => commit("SET_EVENTS", data))
      .catch((error) => console.log(error));
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
