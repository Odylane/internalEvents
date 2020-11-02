<template>
  <v-container id="registration">
    <v-row class="text-center">
      <v-flex class="mb-4">
        <h1 class="display-1 font-weight-bold mb-3">{{ titles.formTitle }}</h1>

        <!--Form to create a user account-->
        <v-form ref="form" v-model="valid" id="form">
          <v-text-field
            v-model="Employee.username"
            :rules="usernameRules"
            label="Identifiant"
            placeholder="matricule RH GL00xxxxxxx"
            required
          ></v-text-field>

          <v-text-field
            v-model="Employee.firstname"
            :rules="firstnameRules"
            label="Prénom"
            required
          ></v-text-field>

          <v-text-field
            v-model="Employee.lastname"
            :rules="lastnameRules"
            label="Nom"
            required
          ></v-text-field>

          <v-text-field
            v-model="Employee.email"
            :rules="emailRules"
            label="Email"
            required
          ></v-text-field>

          <v-text-field
            v-model="Employee.password"
            :rules="passwordRules"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show1 ? 'text' : 'password'"
            label="Mot de passe"
            prepend-inner-icon="mdi-lock"
            @click:append="show1 = !show1"
            required
          ></v-text-field>

          <!--Button calls method "createAccount"-->
          <v-card-actions class="d-flex justify-end">
            <v-btn
              class="white--text"
              :disabled="!valid"
              color="success"
              @click.prevent="createAccount"
              >{{ titles.buttonTitle }}</v-btn
            >
          </v-card-actions>
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
      default: () => ({
        formTitle: "",
        buttonTitle: "",
      }),
    },
  },
  //Link the form's fields to the data of the component
  data: () => ({
    Employee: {
      username: "",
      firstname: "",
      lastname: "",
      email: "",
      password: "",
    },
    valid: true,
    show1: false,
    usernameRules: [
      (value) => !!value || "Veuillez saisir votre identifiant.",
      (value) => {
        const pattern = /^[G][L]0{2}(\d){7}$/;
        return pattern.test(value) || "Identifiant invalide.";
      },
    ],
    firstnameRules: [
      (value) => !!value || "Veuillez saisir votre prénom.",
      (value) => {
        const pattern = /^([a-zâäàéèùêëîïôöçñ .'-]{2,100})$/i;
        return pattern.test(value) || "Saisie invalide.";
      },
    ],
    lastnameRules: [
      (value) => !!value || "Veuillez saisir votre nom.",
      (value) => {
        const pattern = /^([a-zâäàéèùêëîïôöçñ .'-]{2,100})$/i;
        return pattern.test(value) || "Saisie invalide.";
      },
    ],
    emailRules: [
      (value) => !!value || "Veuillez saisir votre email.",
      (value) => {
        const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return pattern.test(value) || "Saisie invalide.";
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
  //Submit the form
  methods: {
    createAccount() {
      let newUser = {
        username: this.Employee.username,
        firstname: this.Employee.firstname,
        lastname: this.Employee.lastname,
        email: this.Employee.email,
        password: this.Employee.password,
      };

      axios.post("http://localhost:8085/api/register", newUser).then(
        (response) => {
          const status = response.request.status;
          if (status == 201) {
            this.$router.push("login");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
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
