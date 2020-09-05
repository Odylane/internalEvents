<template>
  <v-container id="registration">
    <v-row class="text-center">
      <v-flex class="mb-4">
        <h1 class="display-1 font-weight-bold mb-3">{{ titles.formTitle }}</h1>

        <!--Form to create an user account-->
        <v-form ref="form" v-model="valid" id="form">
          <v-container>
            <v-text-field
              v-model="User.username"
              :rules="usernameRules"
              label="Identifiant"
              placeholder="matricule RH GL00xxxxxxx"
              required
            ></v-text-field>

            <v-text-field
              v-model="User.firstname"
              :rules="firstnameRules"
              label="Prénom"
              required
            ></v-text-field>

            <v-text-field
              v-model="User.lastname"
              :rules="lastnameRules"
              label="Nom"
              required
            ></v-text-field>

            <v-text-field
              v-model="User.password"
              :rules="passwordRules"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :type="show1 ? 'text' : 'password'"
              label="Mot de passe"
              prepend-inner-icon="mdi-lock"
              @click:append="show1 = !show1"
              required
            ></v-text-field>

            <!--Button submit calls method "createAccount"-->
            <v-card-actions class="d-flex justify-end">
              <v-btn
                class="white--text"
                :disabled="!valid"
                color="success"
                @click.prevent="createAccount"
              >{{ titles.buttonTitle }}</v-btn>
            </v-card-actions>
            
          </v-container>
        </v-form>
      </v-flex>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "RegistrationForm",
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
    User: {
      username: "",
      firstname:"",
      lastname:"",
      password: ""
    },
    valid: true,
    show1: false,
    usernameRules: [
      value => !!value || "Veuillez saisir votre identifiant.",
      value => {
        const pattern = /^[GL00X]+(\d){7}$/;
        return pattern.test(value) || "Identifiant invalide."; 
      }
    ],
    firstnameRules: [
      value => !!value || "Veuillez saisir votre prénom.",
      value => {
        const pattern = /^(?=.*[a-z])(?=.*[A-Z].{2,100})/;
        return pattern.test(value) || "Saisie invalide."; 
      }
    ],
    lastnameRules: [
      value => !!value || "Veuillez saisir votre nom.",
      value => {
        const pattern = /^(?=.*[a-z])(?=.*[A-Z].{2,100})/;
        return pattern.test(value) || "Saisie invalide."; 
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
    createAccount() {
      let newUser = {
        username: this.User.username,
        firstname: this.User.firstname,
        lastname: this.User.lastname,
        password: this.User.password
      };
      console.log(newUser);
      
      axios.post("http://localhost:8085/events/add", newUser).then(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};
</script>
<!-- "scoped", style only for this component--> 
<style lang="scss" scoped>
#registration {
  margin-top: 30px;
}
#form {
  width: 500px;
  margin: 0 auto;
}
</style>