<template>
  <div class="login">
    <my-header></my-header>
    <EventsList/>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import EventsList from "@/components/EventsList.vue";

export default {
  name: "Events",
  components: {
    "my-header": Header,
    EventsList,
  },
  data() {
    return {
      //
    };
  },
};
</script>
