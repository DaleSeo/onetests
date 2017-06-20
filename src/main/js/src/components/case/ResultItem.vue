<template>
  <div class="ui icon message">
    <ResultHeader :result="result"/>
    <div class="content">
      <div class="header" v-if="result.request">
        {{result.request.method}} {{result.request.host}}{{result.request.path}}
      </div>
      <p>
        <ResultLabel title="상태" :result="result.statusPassed" @click="diffStatus"/>
        <ResultLabel title="헤더" :result="result.headersPassed" @click="diffHeaders"/>
        <ResultLabel title="바디" :result="result.bodyPassed" @click="diffBody"/>
      </p>
      <i class="calendar icon"/>{{result.createdDate | formatDate}}
      <i class="wait icon"/>{{result.elapsedTime === 0 ? 'N/A' : result.elapsedTime}} ms
    </div>
    <Diff :key="result.id" :result="result" :viewIdx="viewIdx"/>
  </div>
</template>

<script>
import Diff from './Diff.vue'

const VIEW_STATUS = 1, VIEW_HEADERS = 2, VIEW_BODY = 3

export default {
  components: {Diff},
  props: ['result'],
  data () {
    return {
      viewIdx: 0
    }
  },
  methods: {
    diffStatus () {
      this.viewIdx = VIEW_STATUS
      this.modalDiff()
    },
    diffHeaders () {
      this.viewIdx = VIEW_HEADERS
      this.modalDiff()
    },
    diffBody () {
      this.viewIdx = VIEW_BODY
      this.modalDiff()
    },
    modalDiff () {
      $(`#${this.result.id}.ui.modal`).modal('show')
    }
  }
}
</script>
