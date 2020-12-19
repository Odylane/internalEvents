<template>
  <v-container id="login">
    <v-row class="text-center">
      <v-flex class="mb-4">
        <h1 class="display-1 font-weight-bold mb-3">{{ titles.formTitle }}</h1>

        <!--Form to log a user-->
        <v-form ref="form" v-model="valid" id="form">
          <v-container>
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
              >{{ titles.buttonTitle }}</v-btn>
            </v-card-actions>
            </div>
            
          </v-container>
        </v-form>
      </v-flex>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginPage",
  props: {
    titles: {
      type: Object,
      default: ()=>({
        formTitle:"",
        buttonTitle:""
      })
    }
  },
  //Link the form's fields to the data of the component
  data: () => ({
    username: "",
    password: "",
    valid: true,
    show1: false,
    usernameRules: [
      value => !!value || "Veuillez saisir votre identifiant.",
      value => {
        const pattern = /^[G][L]0{2}(\d){7}$/;
        return pattern.test(value) || "Identifiant invalide."; 
      }
    ],
    passwordRules: [
      value => !!value || "Veuillez renseigner ce champ.",
      value => {
        const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@§;#$%?/,^&*]).{8,12}$/;
        return pattern.test(value) || "Mot de passe invalide.";
      }
    ]
  }),
  //Submit the form 
  methods: {
    login() {
      let form = new FormData();
      form.set("username", this.username);
      form.set("password", this.password);
      form.set("client_id", "my-ievents-app");
      form.set("grant_type", "password");

    axios.post("http://localhost:8085/oauth/token", form)
      .then((response) => {
          const status = response.request.status;
          if (status == 200) {
            this.$router.push("events");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
  }
</script>
    
<style lang="scss" scoped>
#login {
  margin-top: 30px;
}
#form {
  width: 500px;
  margin: 0 auto;
}
</style>