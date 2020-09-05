<template>
  <div class="home">
    <RegistrationForm :titles="titles"/>
      <v-row class="text-center">
        <v-flex class="mb-4">
          <p>Vous avez déjà un compte ?
            <router-link to="/login">Se connecter</router-link>
          </p>
        </v-flex>
      </v-row>
  </div>
  
</template>

<script>
// @ is an alias to /src
import RegistrationForm from '@/components/RegistrationForm.vue'

export default {
  name: 'Home',
  components: {
    RegistrationForm
  },
  data(){
    return {
      titles:{
        formTitle:"Créer un compte",
        buttonTitle: "Créer un compte"
      }
    }
  }
}
</script>
