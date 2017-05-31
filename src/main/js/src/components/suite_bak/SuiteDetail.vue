<template>
  <div class="container">
    <h3>
      <b>{{suite.title}}</b>
      <div class="btn-group pull-right">
        <button class="btn btn-sm btn-default" title="목록" @click="list">
          <i class="fa fa-list"/>
        </button>
        <button class="btn btn-sm btn-default" title="삭제" @click="del">
          <i class="fa fa-trash"/>
        </button>
      </div>
    </h3>
    <hr/>
    <blockquote>
      <button class="btn btn-sm btn-warning" @click="prepTests">
        <i class="fa fa-industry"/> 적재
      </button>
      <button class="btn btn-sm btn-success" @click="callTests">
        <i class="fa fa-spinner"/> 호출
      </button>
      <button class="btn btn-sm btn-info" @click="diffTests">
        <i class="fa fa-code"/> 비교
      </button>
      <button class="btn btn-sm btn-primary" @click="aggregateTests">
        <i class="fa fa-bar-chart"/> 집계
      </button>
      버튼을 차례로 클릭하여 일괄 검증을 수행하십시오.
      <span v-if="suite.summary" class="pull-right">
        <span class="label label-primary">{{suite.summary.numPass}} passes</span>
        <span class="label label-danger">{{suite.summary.numFail}} fails</span>
      </span>
    </blockquote>
    <Test :suiteId="suiteId"/>
  </div>
</template>

<script>
import Test from '../test/Test.vue'

import db from '../../services/database'
import SuiteService from '../../services/SuiteService'
import TestService from '../../services/TestService'

import moment from 'moment'

let suiteService
let testService

export default {
  props: ['serviceId', 'suiteId'],
  components: {Test},
  created () {
    suiteService = new SuiteService(this.serviceId)
    testService = new TestService(this.suiteId)
  },
  data () {
    return {
      modal: false
    }
  },
  firebase () {
    return {
      suite: {
        source: db.ref(`/services/${this.serviceId}/suites/${this.suiteId}`),
        asObject: true
      }
    }
  },
  methods: {
    list () {
      window.location.href = `/services/${this.serviceId}`
    },
    prepTests () {
      console.log('#prepTests')
      testService.prep()
        .then(_ => console.log('Done!'))
        .catch(err => console.error(err))
    },
    callTests () {
      console.log('#callTests')
      testService.call()
        .then(_ => console.log('Done!'))
        .catch(err => console.error(err))
    },
    diffTests () {
      console.log('#diffTests')
      testService.diff()
    },
    aggregateTests () {
      console.log('#diffTests')
      testService.aggregate()
        .then(summary => suiteService.modify(this.suiteId, 'summary', summary))
        .then(_ => console.log('Suite modified'))
        .catch(err => console.error(err))
    },
    del () {
      console.log('#del:', this.suiteId)
      suiteService.remove(this.suiteId)
      window.history.back()
    }
  }
}
</script>

<style scoped>
blockquote {
  font-size: 12pt;
}
</style>
