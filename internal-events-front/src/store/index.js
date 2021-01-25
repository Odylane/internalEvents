import Vue from "vue";
import Vuex from "vuex";

import event from "./modules/event";
import profile from "./modules/profile";
import auth from "./modules/auth";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    event,
    profile,
  },
});