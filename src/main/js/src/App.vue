<template>
  <Layout id="app"/>
</template>

<script>
import Vue from 'vue'

import Layout from './components/Layout.vue'

import Title from './components/common/Title.vue'
import RequestLine from './components/common/RequestLine.vue'
import Method from './components/common/Method.vue'
import ResultLabel from './components/common/ResultLabel.vue'
import ServiceDropdown from './components/common/ServiceDropdown.vue'
import HostDropdown from './components/common/HostDropdown.vue'
import ServiceFilter from './components/common/ServiceFilter.vue'
import CaseSearch from './components/common/CaseSearch.vue'

Vue.component('Title', Title)
Vue.component('RequestLine', RequestLine)
Vue.component('Method', Method)
Vue.component('ResultLabel', ResultLabel)
Vue.component('ServiceDropdown', ServiceDropdown)
Vue.component('HostDropdown', HostDropdown)
Vue.component('ServiceFilter', ServiceFilter)
Vue.component('CaseSearch', CaseSearch)

export default {
  components: { Layout },
}
</script>
