<template>
  <div class="ui divided selection animated list">
    <div class="item" v-for="caseResult in caseResults">
      <div class="right floated content">
        <ResultLabel :result="caseResult"/>
      </div>
      <i class="right triangle icon"/>
      <div class="content">
        <a class="header" :href="/calls/ + caseResult.callId">{{caseResult.callId}}</a>
        <div class="description">{{caseResult.createdDate | formatDate}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['resultId'],
  data () {
    return {
      caseResults: []
    }
  },
  watch: {
    resultId () {
      this.fetchCaseResults()
    }
  },
  created () {
    this.fetchCaseResults()
  },
  methods: {
    fetchCaseResults () {
      this.$http.get(`/api/suiteResults/${this.resultId}/caseResults`)
        .then(res => res.body._embedded.caseResults)
        .then(caseResults => this.caseResults = caseResults)
    }
  }
}
</script>
