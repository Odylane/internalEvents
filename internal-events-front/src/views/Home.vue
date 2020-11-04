<template>
  <div class="home">
    <my-header></my-header>
    <RegistrationForm :titles="titles"/>
      <v-row class="text-center">
        <v-flex class="mb-4">
          <p>Vous avez déjà un compte ?
            <router-link to='/login'>Se connecter</router-link>
          </p>
        </v-flex>
      </v-row>
  </div>
  
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import RegistrationForm from '@/components/RegistrationForm.vue'

export default {
  name: 'Home',
  components: {
    'my-header' :Header,
    RegistrationForm
  },
  data(){
    return {
      titles:{
        formTitle:"Créer un compte",
        buttonTitle: "OK"
      }
    }
  }
}
</script>
