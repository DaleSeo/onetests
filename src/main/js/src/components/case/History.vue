<template>
  <div>
    <button class="ui primary fluid button" :class="{loading}" @click="run">
      <i class="play icon"/>테스트 실행
    </button>
    <div class="ui divided selection animated list">
      <div class="item" v-for="call in calls">
        <div class="right floated content">
          <ResultLabel :result="call.result"/>
        </div>
        <i class="right triangle icon"/>
        <div class="content">
          <a class="header" :href="/calls/ + call.id">{{call.id}}</a>
          <div class="description">{{call.createdDate | formatDate}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import testRunner from '../../services/testRunner'
import callSvc from '../../services/callSvc'

export default {
  props: ['caseId'],
  data () {
    return {
      calls: [],
      loading: false
    }
  },
  created () {
    this.fetchCalls()
  },
  methods: {
    fetchCalls () {
      callSvc.list({caseId: this.caseId})
        .then(calls => this.calls = calls)
        .catch(err => toastr.error('호출 이력 조회 실패'))
    },
    run () {
      this.loading = true
      testRunner.runSingle(this.caseId)
        .then(call => toastr.success('테스트 실행 완료'))
        .catch(err => toastr.error('테스트 실행 실패'))
        .then(_ => this.fetchCalls())
        .then(_ => this.loading = false)
    },
  }
}
</script>
