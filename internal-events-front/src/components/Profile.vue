<template>
  <v-container>
    <v-row justify="center">
      <v-col>
        <v-card class="mx-lg-auto" max-width="1000">
          <v-card-text>
            <h1 class="display-1 font-weight-bold mb-6 text-center">
              Mon profil
            </h1>

            <div>{{ authData.userId }}</div>

            <div>{{ currentEmployee.username }}</div>

            <div>{{ currentEmployee.firstname }}</div>

            <div>{{ currentEmployee.lastname }}</div>

            <div>{{ currentEmployee.email }}</div>
          </v-card-text>
          <v-card-actions>
            <BackEvents />
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import BackEvents from "../components/BackEvents";

export default {
  name: "Profile",
  components: { BackEvents },

  computed: {
    // ...mapGetters("auth", ["getAuthData"]),
    ...mapState("auth", ["currentEmployee","authData"]),
  },
  // mounted() {
  //   this.$store.dispatch("auth/getCurrentEmployee");
  // },
};
</script>
