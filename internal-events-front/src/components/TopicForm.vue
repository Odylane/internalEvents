<template>
  <v-container>
    <v-form class="ma-10 pa-10" v-model="valid" id="formTopic">
      <h1 class="display-1 font-weight-bold mb-2 text-center">
        Ajouter un thème
      </h1>

      <div>
        <v-text-field
          v-model="name"
          :counter="45"
          :rules="nameRules"
          hint="Le nom du thème ne doit pas dépasser 45 caractères"
          label="Nom du thème*"
          required
        ></v-text-field>
      </div>

      <small>*Champ obligatoire</small>

      <v-card-actions>
        <v-btn
          class="white--text d-flex justify-end"
          :disabled="!valid"
          color="success"
          @click.prevent="saveTopic"
          >Sauvegarder</v-btn
        >
      </v-card-actions>
    </v-form>

    <v-btn class="ml-10"
      ><router-link to="/topics"
        >Retour à la liste des thèmes</router-link
      ></v-btn
    >
    <v-snackbar
      :color="snackbar.color"
      v-model="snackbar.show"
      :multi-line="multiLine"
    >
      {{ snackbar.message }}

      <template v-slot:action="{ attrs }">
        <v-btn color="pink" text v-bind="attrs" @click="snackbar">
          Fermer
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import jwtInterceptor from "@/jwtInterceptor";

export default {
  name: "TopicForm",
  data: () => ({
    name: "",
    valid: true,
    nameRules: [
      (value) => !!value || "Veuillez saisir un nom (45 caractères maximum).",
      (value) => (value && value.length <= 45) || "Nom invalide.",
    ],
    multiLine: true,
    snackbar: {
      show: false,
      message: null,
      color: null
      }
  }),
  methods: {
    saveTopic() {
      let data = {
        name: this.name,
      };

      jwtInterceptor.post("http://localhost:8085/api/topics", data).then(
        (response) => {
          const status = response.request.status;
          if (status == 201) {
            this.snackbar = {
              message: 'Le thème est sauvegardé',
              color: 'success',
              show: true
          }; setTimeout( () => this.$router.push("/topics"), 3000);
          }
        })
        .catch((error) => {
          console.log(error);
        }
        )},
  },
};
</script>

<style lang="scss" scoped>
#formTopic {
  width: 800px;
  padding: 1em;
  border: 1px solid #ccc;
}

form div + div {
  margin-top: 1em;
}
</style>
