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
import RequestLine from './components/common/RequestLine.vue'
import Method from './components/common/Method.vue'
import ResultLabel from './components/common/ResultLabel.vue'

Vue.component('Title', Title)
Vue.component('RequestLine', RequestLine)
Vue.component('Method', Method)
Vue.component('ResultLabel', ResultLabel)

export default {
  components: { Header1, Footer1 },
  mounted () {
    $('#headerDiv .ui.dropdown').dropdown()
  }
}
</script>
