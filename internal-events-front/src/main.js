import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import axios from "axios";

//baseURL: A relative URL that acts as a prefix to all requests
axios.defaults.baseURL = "http://localhost:8085/api/";

export default axios;

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
