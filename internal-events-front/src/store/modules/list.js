import axios from "axios";

const state = {
  eventTypes: [],
  topics: [],
  rooms: [],
};

const mutations = {
  SET_EVENT_TYPES(state, eventTypes) {
    state.eventTypes = eventTypes;
  },
  SET_TOPICS(state, topics) {
    state.topics = topics;
  },
  SET_ROOMS(state, rooms) {
    state.rooms = rooms;
  },
  
};

const actions = {
  getAllEventTypes({ commit }) {
    axios
      .get("eventTypes")
      .then(({ data }) => commit("SET_EVENT_TYPES", data))
      .catch((error) => console.log(error));
  },
  getAllTopics({ commit }) {
    axios
      .get("topics")
      .then(({ data }) => commit("SET_TOPICS", data))
      .catch((error) => console.log(error));
  },
  getAllRooms({ commit }) {
    axios
      .get("rooms")
      .then(({ data }) => commit("SET_ROOMS", data))
      .catch((error) => console.log(error));
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
