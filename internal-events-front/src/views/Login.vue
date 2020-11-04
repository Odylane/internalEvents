<template>
  <div class="login">
        <my-header></my-header>
        <LoginPage :titles="titles"/>
        
  </div>
</template>

<script>

import Header from '@/components/Header.vue'
import LoginPage from '@/components/LoginPage.vue'

export default {
  name: 'Login',
  components: {
    'my-header': Header,
    LoginPage
  },
  data(){
    return {
      titles:{
        formTitle:"Se connecter",
        buttonTitle: "OK",
        buttonCancel: "Cancel"
      }
    }
  }
}
</script>