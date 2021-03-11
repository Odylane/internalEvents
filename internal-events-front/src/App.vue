<template>
  <v-app>
    <v-app-bar app color="indigo" dark>
      <v-icon left>
        mdi-alpha-l-box
      </v-icon>

      <v-toolbar-title
        class="text-uppercase"
        style="cursor: pointer"
        @click="$router.push('/events')"
        >Internal Events</v-toolbar-title
      >
      <v-spacer></v-spacer>
      <v-list color="indigo">
        <v-list-item>
          <v-btn
            class="button"
            small
            v-if="authData.token"
            to="/topics"
            tag="ul"
            >Admin
          </v-btn>
          <v-menu offset-y v-if="authData.token">
            <template v-slot:activator="{ on, attrs }">
              <v-btn class="button" small white--text v-bind="attrs" v-on="on">
                Gérer mes événements
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in items"
                :key="index"
                :to="item.route"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-btn class="button"
            small
            v-if="authData.token"
            @click.prevent="logoutEmployee"
            tag="ul"
          >
            se déconnecter
          </v-btn>

          <v-btn
            small
            v-if="authData.token"
            color="indigo"
            to="/profile"
            tag="ul"
            ><v-icon>
              mdi-account-circle
            </v-icon></v-btn
          >
        </v-list-item>
      </v-list>
    </v-app-bar>
    <v-main>
      <router-view></router-view>
    </v-main>

    <v-footer color="grey" app>
      <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "App",

  data: () => ({
    items: [
      { title: "Créer un événement", route: "/events/create" },
    ],
  }),

  computed: {
    ...mapState("auth", ["authData"]),
  },
  methods: {
    ...mapActions("auth", ["logout"]),
    logoutEmployee() {
      this.logout();
      this.$router.push("/logout");
    },
  },
};
</script>

<style lang="scss" scoped>
.button {
  margin-right: 10px;
  background-color: #3F51B5 !important;
  
}
</style>
