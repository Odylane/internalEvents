<template>

  <v-app id="inspire">
    
    <v-app-bar app color="indigo" dark>
      <TopNavbar/>
      
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>

    <v-footer color="grey" app>
      <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
    
  </v-app>

</template>

<script>
import TopNavbar from "@/components/TopNavbar.vue";

export default {
  name: "App",
  components: {
    TopNavbar,
  },
  data: () => ({
    //
  }),
};
</script>
