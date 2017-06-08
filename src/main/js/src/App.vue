<template>
  <div>
    <div class="ui inverted vertical segment" style="padding: 0">
      <Header1/>
    </div>
    <div class="ui vertical segment" style="padding: 0">
      <br v-if="!isHome"/>
      <router-view></router-view>
    </div>
    <br/>
    <div class="ui inverted vertical segment">
      <Footer1/>
    </div>
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
import ServiceDropdown from './components/common/ServiceDropdown.vue'
import HostDropdown from './components/common/HostDropdown.vue'
import ServiceFilter from './components/common/ServiceFilter.vue'

Vue.component('Title', Title)
Vue.component('RequestLine', RequestLine)
Vue.component('Method', Method)
Vue.component('ResultLabel', ResultLabel)
Vue.component('ServiceDropdown', ServiceDropdown)
Vue.component('HostDropdown', HostDropdown)
Vue.component('ServiceFilter', ServiceFilter)

export default {
  components: { Header1, Footer1 },
  computed: {
    isHome () {
      return this.$route.path === '/'
    }
  },
  mounted () {
    $('#headerDiv .ui.dropdown').dropdown()
  }
}
</script>
