<template>
  <div>
    <br/>
    <h5 class="ui horizontal divider header">
      <i class="browser icon"/>호출 이력
    </h5>

    <div class="ui divided selection animated list">
      <div class="item" v-for="call in calls">
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
