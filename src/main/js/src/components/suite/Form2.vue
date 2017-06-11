<template>
  <div class="ui grid">
    <div class="row">
      <div class="center aligned column">
        <pre>{{resultId}}</pre>
        <ResultView
          :resultId="resultId"
          v-if="resultId"
        />
        <ResultList
          :results="results"
          v-if="!resultId && results"
          @view="view"
        />
        <div class="ui fluid info message" v-if="!resultId && !results">
          <div class="header">
            실행 버튼을 눌러 테스트 스위트를 실행해주세요.
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="right aligned column">
        <button class="ui labeled icon button" @click="back">
          <i class="step backward icon"/>이전
        </button>
        <button class="ui labeled icon button" @click="run">
          <i class="play icon"/>실행
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import testRunner from '../../services/testRunner'
import ResultList from './ResultList.vue'
import ResultView from './ResultView.vue'

export default {
  components: {ResultList, ResultView},
  props: ['suite'],
  data () {
    return {
      cases: [],
      results: {},
      resultId: ''
    }
  },
  created () {
    this.fetchResults()
  },
  methods: {
    back () {
      this.$emit('changeStep', 1)
    },
    fetchResults () {
      let url = `/api/suiteResults/search/bySuite?id=${this.suite.id}&sort=id,desc`
      console.log('#url:', url)
      return this.$http.get(url)
        .then(res => res.body._embedded.suiteResults)
        .then(results => this.results = results)
    },
    run () {
      testRunner.runSuite(this.suite.id)
        .then(this.fetchResults)
        .then(_ => toastr.success('테스트가 완료되었습니다'))
        .catch(err => toastr.error('테스트 스위트 실행 실패'))
    },
    view (id) {
      this.resultId = id
    }
  }
}
</script>
