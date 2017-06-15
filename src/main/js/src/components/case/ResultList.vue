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
  props: ['results'],
  methods: {
    callDetail (result) {
      console.log('#callDetail:', result.callId)
      location.href = `/calls/${result.callId}`
    }
  }
}
</script>
