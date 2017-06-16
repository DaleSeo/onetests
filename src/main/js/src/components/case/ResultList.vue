<template>
  <div class="ui list">
    <div class="ui icon message" v-for="result in results" @click="callDetail(result)">
      <ResultHeader :result="result"/>
      <div class="content">
        <div class="header">
          {{title(result)}}
        </div>
        <p>
          <ResultItem title="상태" :result="result.statusPassed"/>
          <ResultItem title="헤더" :result="result.headersPassed"/>
          <ResultItem title="바디" :result="result.bodyPassed"/>
        </p>
        <i class="calendar icon"/>{{result.createdDate | formatDate}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['results'],
  methods: {
    title (result) {
      return result.passed ? '검증 성공' : '검증 실패'
    },
    callDetail (result) {
      console.log('#callDetail:', result.callId)
      location.href = `/calls/${result.callId}`
    }
  }
}
</script>
