<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <strong>검증 목록</strong> <small>({{suites.length}})</small>
      <button class="btn btn-xs btn-primary pull-right" @click="showForm"><i class="fa fa-pencil-square-o"/> 등록</button>
    </div>
    <table class="table table-hover table-bordered">
      <thead>
        <tr>
          <th class="text-center">이름</th>
          <th class="text-center">API수</th>
          <th class="text-center">호스트A</th>
          <th class="text-center">호스트B</th>
          <th class="text-center">일시</th>
          <th class="text-center">상태</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="suite['.key']" v-for="suite in suites" @click="pick(suite['.key'])">
          <td>{{suite.title}}</td>
          <td class="text-center">{{suite.apis.length}}</td>
          <td>{{suite.hostA}}</td>
          <td>{{suite.hostB}}</td>
          <td>{{suite.date | formatDate}}</td>
          <td>{{status(suite.summary)}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import moment from 'moment'

export default {
  props: ['suites', 'serviceId', 'form'],
  filters: {
    formatDate (date) {
      return moment(date).format('YY/MM/DD HH:mm:ss')
    }
  },
  methods: {
    showForm () {
      this.$emit('showForm')
    },
    status (summary) {
      if (!summary) {
        return "READY"
      }
      if (summary.numFail == 0) {
        return "PASS"
      }
      return "FAIL"
    },
    pick (suiteId) {
      window.location.href = `/services/${this.serviceId}/suites/${suiteId}`
    }
  }
}
</script>
