<template>
  <div class="login">
        <RegistrationForm :titles="titles"/>
        
  </div>
</template>

<script>

import RegistrationForm from '@/components/RegistrationForm.vue'

export default {
  name: 'Login',
  components: {
    RegistrationForm
  },
  data(){
    return {
      titles:{
        formTitle:"Se connecter",
        buttonTitle: "Login"
      }
    }
  }
}
</script>