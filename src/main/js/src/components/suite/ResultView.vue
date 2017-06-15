<template>
  <div class="ui items">
    <div class="item" v-for="result in results" @click="callDetail(result)">
      <div class="ui tiny image">
        <ResultHeader :result="result"/>
      </div>
      <div class="middle aligned content">
        <div class="meta">
          <i class="calendar icon"/>
          {{result.createdDate | formatDate}}
        </div>
        <div class="description">
          <ResultItem title="상태" :result="result.statusPassed"/>
          <ResultItem title="헤더" :result="result.headersPassed"/>
          <ResultItem title="바디" :result="result.bodyPassed"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['resultId'],
  data () {
    return {
      results: []
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
        .then(caseResults => this.results = caseResults)
    }
  }
}
</script>
