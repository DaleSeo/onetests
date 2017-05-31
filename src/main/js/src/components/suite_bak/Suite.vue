<template>
  <div>
    <SuiteList :suites="suites" :serviceId="id" :form="form" @showForm="form = true"/>
    <SuiteForm :suite="suite" :serviceId="id" @add="add" @hideForm="form = false" v-if="form"/>
  </div>
</template>

<script>
import SuiteList from './SuiteList.vue'
import SuiteForm from './SuiteForm.vue'

import db from '../../services/database'
import SuiteService from '../../services/SuiteService'
import TestService from '../../services/TestService'

let suiteService

export default {
  props: ['id'],
  components: {SuiteList, SuiteForm},
  created () {
    suiteService = new SuiteService(this.id)
  },
  data () {
    return {
      suiteService: null,
      form: false,
      suite: this.initSuite()
    }
  },
  firebase () {
    return {
      suites: db.ref(`services/${this.id}/suites`)
    }
  },
  methods: {
    initSuite () {
      return {
        date: null,
        title: '',
        completed: false,
        hostA: '',
        hostB: '',
        apis: []
      }
    },
    add () {
      console.log('# add')
      console.log(this.suite)
      suiteService.create(this.suite)
        .then(key => {
          suiteService.find(key).then(suite =>
            new TestService(key).initialize(suite)
          )
        })
        .catch(err => console.error(err))
      this.suite = this.initSuite()
    }
  }
}
</script>
