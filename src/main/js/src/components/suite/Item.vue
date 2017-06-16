<template>
  <tr @click="detail">
    <td>{{suite.title}}</td>
    <td>{{numCases}}</td>
    <td>{{suite.service.code}}</td>
    <td>{{suite.host.baseUrl}}</td>
    <td>{{suite.createdBy | userName}}</td>
    <td>{{suite.createdDate | formatDate}}</td>
  </tr>
</template>

<script>
export default {
  props: ['suite'],
  computed: {
    numCases () {
      let caseIds = this.suite.caseIds
      return caseIds ? Object.keys(caseIds).length : 0
    }
  },
  methods: {
    detail () {
      window.location.href = `/suites/${this.suite.id}`
    }
  }
}
</script>
