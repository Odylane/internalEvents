import Vue from "vue";
import Vuex from "vuex";

import event from "./modules/event";
import auth from "./modules/auth";
import list from "./modules/list";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    event,
    list,
  },
});