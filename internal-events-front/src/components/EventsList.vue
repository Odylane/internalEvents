<template>
  <v-container class="blue lighten-5">
    <v-row class="list px-12">
      <v-col v-for="event in events" :key="event" class="d-flex">
        <!-- "tile" card's form-->
        <v-card class="d-flex flex-column" width="300px" height="100%" tile>
          <v-card-text>
            <div class="overline mb-1">{{ event.nameEventType }}</div>
            
            <p class="title text--primary">{{ event.title }}</p>
            <v-cart-text class="subtitle-1 ">{{formatDateTime(event.startDateTime)}} <br />
            <span class="subtitle-2">{{ event.nameBuilding }}  <br />
              <div v-if= "event.nameRoom === 'N/A' "></div><div v-else>salle {{ event.nameRoom}}</div></span></v-cart-text>  

          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import EventDataService from "../services/EventDataService";
import moment from "moment";

export default {
  name: "events-list",
  data() {
    return {
      events: [],
    };
  },
  methods: {
    formatDateTime(dateTime) {
      moment.locale("fr");
      return moment(dateTime).format("ddd ll [à] LT");
    },
    retrieveEvents() {
      EventDataService.getAll()
        .then((response) => {
          this.events = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.retrieveEvents();
  },
};
</script>

<style>
.list {
  max-width: 100%;
}
</style>
