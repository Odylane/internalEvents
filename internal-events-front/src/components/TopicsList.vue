<template>
  <v-container>
    <v-card class="mt-10 mx-10 mb-5 pa-10 mx-auto" max-width="1000">
    <h1 class="display-1 font-weight-bold mb-2 text-center">
      Liste des thèmes
    </h1>
    <v-card-actions>
      <v-btn
        color="success"
        class="ma-10 mr-4"
        to="/topics/add"
        @click="addTopic"
      >
        Ajouter un thème
      </v-btn>
    </v-card-actions>
    <v-simple-table fixed-header height="300px">
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left font-weight-bold">
              NOM
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="topic in topics" :key="topic.id">
            <td>{{ topic.name }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "TopicsList",
  ddata: () => ({
    valid: true,
    show1: false,
  }),

  created() {
    this.getAllTopics();
  },
  computed: {
    ...mapState("list", ["topics"]),
  },
  methods: {
    ...mapActions("list", ["getAllTopics"]),
  },
};
</script>

<style></style>
