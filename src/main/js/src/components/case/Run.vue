<template>
  <div>
    <div class="ui grid">
      <div class="right aligned column">
        <EnvironmentDropdown v-model="environment" size="tiny"/>
        <button class="ui labeled icon primary tiny button" :class="{loading}" @click="run">
          <i class="rocket icon"/>검증 수행
        </button>
      </div>
    </div>
    <ResultList :results="results"/>
  </div>
</template>

<script>
import testRunner from '../../services/testRunner'
import EnvironmentDropdown from '../common/EnvironmentDropdown.vue'
import ResultList from './ResultList.vue'

export default {
  components: {EnvironmentDropdown, ResultList},
  props: ['caseId'],
  data () {
    return {
      results: [],
      environment: null,
      loading: false
    }
  },
  created () {
    this.fetchResults()
  },
  methods: {
    fetchResults () {
      let url = `/api/caseResults/search/byCase?id=${this.caseId}&sort=id,desc`
      console.log('#url:', url)
      return this.$http.get(url)
        .then(res => res.body._embedded.caseResults)
        .then(results => this.results = results)
        .catch(err => toastr.error('테스트 결과 조회 실패'))
    },
    run () {
      this.loading = true
      testRunner.runCase(this.caseId, this.environment)
        .then(this.fetchResults)
        .then(_ => toastr.success('테스트가 완료되었습니다.'))
        .catch(err => toastr.error('테스트 케이스 실행 실패'))
        .then(_ => this.loading = false)
    }
  }
}
</script>
