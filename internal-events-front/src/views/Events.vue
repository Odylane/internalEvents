<template>
  <v-container>
    <v-row justify="center">
      <h1>Evénements internes</h1>

      <EventsList />

    </v-row>
  </v-container>
</template>

<script>
import EventsList from "@/components/EventsList.vue";

export default {
  name: "Events",
  components: {
    EventsList,
  },
};
</script>
