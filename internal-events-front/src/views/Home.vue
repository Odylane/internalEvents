<template>
  <div class>
    <LoginPage/>
    <v-row class="text-center">
      <v-flex class="mb-4">
        <p>
          Vous n'avez pas de compte ?
          <router-link to="/register">Créer un compte</router-link>
        </p>
      </v-flex>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import LoginPage from "@/components/LoginPage.vue";

export default {
  name: "Home",
  components: {
    LoginPage,
  },
};
</script>
