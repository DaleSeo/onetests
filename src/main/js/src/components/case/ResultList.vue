<template>
  <div class="ui list">
    <div class="ui icon message" v-for="result in results" @click="callDetail(result)">
      <ResultHeader :result="result"/>
      <div class="content">
        <div class="header" v-if="result.request">
          <Method :method="result.request.method"/> {{result.request.host}}{{result.request.path}}
        </div>
        <p>
          <ResultItem title="상태" :result="result.statusPassed"/>
          <ResultItem title="헤더" :result="result.headersPassed"/>
          <ResultItem title="바디" :result="result.bodyPassed"/>
        </p>
        <i class="calendar icon"/>{{result.createdDate | formatDate}}
        <i class="wait icon"/>{{result.elapsedTime === 0 ? 'N/A' : result.elapsedTime}} ms
      </div>
      <Diff :key="result.id" :result="result"/>
    </div>
  </div>
</template>

<script>
import Diff from './Diff.vue'

export default {
  components: {Diff},
  props: ['results'],
  methods: {
    // title (result) {
    //   return result.passed ? '검증 성공' : '검증 실패'
    // },
    callDetail (result) {
      console.log('#callDetail:', result.callId)
    //  location.href = `/calls/${result.callId}`
      $('#' + result.id + '.ui.modal').modal('show')
    }
  }
}
</script>
