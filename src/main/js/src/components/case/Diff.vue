<template>
  <div :id="result.id" class="ui modal" :class="{fullscreen}">
    <div class="ui header">
      <i class="law icon"></i>
      {{title}}
    </div>
    <div class="content" v-if="result.expected && result.actual">
      <component :is="view" :result="result"/>
    </div>
    <div class="actions">
      <div class="ui basic cancel button">
        <i class="remove icon"/>닫기
      </div>
    </div>
  </div>
</template>

<script>
import DiffStatus from './DiffStatus.vue'
import DiffHeaders from './DiffHeaders.vue'
import DiffBody from './DiffBody.vue'

const VIEW_STATUS = 1, VIEW_HEADERS = 2, VIEW_BODY = 3

export default {
  components: {DiffStatus, DiffHeaders, DiffBody},
  props: {
    result: {
      type: Object,
      default: {
        expected: {},
        actual: {}
      }
    },
    viewIdx: Number
  },
  computed: {
    title () {
      switch (this.viewIdx) {
        case 1: return '상태 대조'
        case 2: return '헤더 대조'
        case 3: return '바디 대조'
        default: return null
      }
    },
    fullscreen () {
      return this.viewIdx !== 1
    },
    view () {
      switch (this.viewIdx) {
        case 1: return DiffStatus
        case 2: return DiffHeaders
        case 3: return DiffBody
        default: return null
      }
    }
  }
}
</script>
