<template>
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
</template>

<script>
import callSvc from '../../services/callSvc'

export default {
  props: ['caseId'],
  data () {
    return {
      calls: []
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
    }
  }
}
</script>
