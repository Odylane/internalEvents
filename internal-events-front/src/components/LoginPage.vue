<template>
  <v-container class="login">
    <v-row>
      <v-flex class="mb-4">
        <h1 class="display-1 font-weight-bold mb-15 text-center">Se connecter</h1>

        <v-form ref="form" v-model="valid" id="form">
          <v-alert dense outlined type="error" v-if="error">{{
            error
          }}</v-alert>

          <v-text-field
            v-model="username"
            :rules="usernameRules"
            label="Identifiant"
            placeholder="matricule RH GL00xxxxxxx"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :rules="passwordRules"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show1 ? 'text' : 'password'"
            label="Mot de passe"
            prepend-inner-icon="mdi-lock"
            @click:append="show1 = !show1"
            required
          ></v-text-field>

          <div class="d-flex justify-end">
            <v-card-actions>
              <!--Button calls method "LogAccount"-->
              <v-btn
                class="white--text"
                :disabled="!valid"
                color="success"
                @click.prevent="login"
                >OK
              </v-btn>
            </v-card-actions>
          </div>
        </v-form>
      </v-flex>
    </v-row>
  </v-container>
</template>

<script>
//import axios from "axios";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "LoginPage",

  //Link the form's fields to the data of the component
  data: () => ({
    username: "",
    password: "",
    error: "",
    valid: true,
    show1: false,
    usernameRules: [
      (value) => !!value || "Veuillez saisir votre identifiant.",
      (value) => {
        const pattern = /^[G][L]0{2}(\d){7}$/;
        return pattern.test(value) || "Identifiant invalide.";
      },
    ],
    passwordRules: [
      (value) => !!value || "Veuillez renseigner ce champ.",
      (value) => {
        const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@§;#$%?/,^&*]).{8,12}$/;
        return pattern.test(value) || "Mot de passe invalide.";
      },
    ],
  }),
  computed: {
    ...mapGetters("auth", {
      getterLoginStatus: "getLoginStatus",
    }),
  },
  methods: {
    ...mapActions("auth", {
      actionLogin: "login",
    }),
    async login() {
      let data = new FormData();
      data.set("username", this.username);
      data.set("password", this.password);
      data.set("client_id", "my-ievents-app");
      data.set("grant_type", "password");

    await this.actionLogin(data);
      if (this.getterLoginStatus === "success") {
        this.$router.push("events");
      } else {
        this.error = "Les données saisies sont erronées";
      }
    },
  },
};

</script>

<style lang="scss" scoped>
.login {
  margin-top: 30px;
}

#form {
  width: 500px;
  margin: 0 auto;
}
</style>
