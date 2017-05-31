<template>
  <div>
    <Header1/>
    <br/><br/><br/><br/><br/>
    <router-view></router-view>
    <br/>
    <Footer1/>
  </div>
</template>

<script>
import Vue from 'vue'

import Header1 from './components/Header.vue'
import Footer1 from './components/Footer.vue'
import Title from './components/common/Title.vue'

Vue.component('Title', Title)

export default {
  components: { Header1, Footer1 }
}
</script>
