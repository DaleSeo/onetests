<template>
  <div class="ui container">
    <Title icon="cubes" title="Test Suite" subTitle="일괄 검증"/>
    <DetailBasics :suite="suite"/>
    <div class="ui grid">
      <div class="right aligned column">
        <EnvironmentDropdown v-model="environment" size="tiny"/>
        <button class="ui labeled icon primary tiny button" :class="{loading: inProgress}" @click="run">
          <i class="rocket icon"/>검증 수행
        </button>
        <button class="ui labeled icon pink tiny button" @click="edit">
          <i class="edit icon"/>스위트 수정
        </button>
      </div>
    </div>
    <h5 class="ui horizontal divider header">
      <i class="browser icon"/>검증 결과
    </h5>
    <div class="ui internally celled grid">
      <div class="four wide column">
        <ResultList
          :results="results"
          @view="view"
        />
      </div>
      <div class="twelve wide column">
        <ResultView
          :resultId="resultId"
          v-if="resultId"
        />
      </div>
    </div>
  </div>
</template>

<script>
import suiteSvc from '../../services/suiteSvc'
import testRunner from '../../services/testRunner'
import ResultList from './ResultList.vue'
import ResultView from './ResultView.vue'
import DetailBasics from './DetailBasics.vue'
import EnvironmentDropdown from '../common/EnvironmentDropdown.vue'

export default {
  components: {ResultList, ResultView, DetailBasics, EnvironmentDropdown},
  props: ['id'],
  data () {
    return {
      suite: {},
      results: [],
      resultId: '',
      environment: null,
      inProgress: false
    }
  },
  created () {
    this.fetchSuite()
    this.fetchResults()
  },
  methods: {
    fetchSuite () {
      suiteSvc.detail(this.id)
        .then(suite => this.suite = suite)
        .catch(err => toastr.error('스위트 상세 조회 실패'))
    },
    fetchResults () {
      let url = `/api/suiteResults/search/bySuite?id=${this.id}&sort=id,desc`
      console.log('#url:', url)
      return this.$http.get(url)
        .then(res => res.body._embedded.suiteResults)
        .then(results => this.results = results)
        .catch(err => toastr.error('테스트 결과 조회 실패'))
    },
    run () {
      this.inProgress = true
      testRunner.runSuite(this.suite.id, this.environment)
        .then(this.fetchResults)
        .then(_ => toastr.success('테스트가 완료되었습니다'))
        .catch(err => toastr.error('테스트 스위트 실행 실패'))
        .then(_ => this.inProgress = false)
    },
    edit() {

    },
    view (id) {
      console.log('#view:', id)
      this.resultId = id
    }
  }
}
</script>
