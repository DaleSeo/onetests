<template>
  <Layout id="app"/>
</template>

<script>
import Vue from 'vue'

import Layout from './components/Layout.vue'

import Title from './components/common/Title.vue'
import RequestLine from './components/common/RequestLine.vue'
import Method from './components/common/Method.vue'
import Pairs from './components/common/Pairs.vue'
import ResultLabel from './components/common/ResultLabel.vue'
import ResultHeader from './components/common/ResultHeader.vue'
import MethodDropdown from './components/common/MethodDropdown.vue'
import ServiceDropdown from './components/common/ServiceDropdown.vue'
import HostDropdown from './components/common/HostDropdown.vue'
import ServiceFilter from './components/common/ServiceFilter.vue'
import HostFilter from './components/common/HostFilter.vue'
import CaseSearch from './components/common/CaseSearch.vue'
import CaseList from './components/common/CaseList.vue'
import CaseItem from './components/common/CaseItem.vue'

Vue.component('Title', Title)
Vue.component('RequestLine', RequestLine)
Vue.component('Method', Method)
Vue.component('Pairs', Pairs)
Vue.component('ResultLabel', ResultLabel)
Vue.component('ResultHeader', ResultHeader)
Vue.component('MethodDropdown', MethodDropdown)
Vue.component('ServiceDropdown', ServiceDropdown)
Vue.component('HostDropdown', HostDropdown)
Vue.component('ServiceFilter', ServiceFilter)
Vue.component('HostFilter', HostFilter)
Vue.component('CaseSearch', CaseSearch)
Vue.component('CaseList', CaseList)
Vue.component('CaseItem', CaseItem)

export default {
  components: { Layout },
}
</script>
