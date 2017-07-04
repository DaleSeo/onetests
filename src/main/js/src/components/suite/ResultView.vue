<template>
  <div class="ui list">
    <ResultItem :key="result.id" :result="result" v-for="result in results"/>
  </div>
</template>

<script>
import ResultItem from '../case/ResultItem.vue'

export default {
  components: {ResultItem},
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
      this.$http.get(`/api/suiteResults/${this.resultId}/caseResults?projection=inline`)
        .then(res => res.body._embedded.caseResults)
        .then(caseResults => this.results = caseResults)
    }
  }
}
</script>
