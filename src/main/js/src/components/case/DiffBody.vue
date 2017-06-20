<template>
  <div class="ui three column internally celled grid">
    <div class="column">
      <h5 class="ui header">기대 바디</h5>
      <pre>{{result.expected.body}}</pre>
    </div>
    <div class="column">
      <h5 class="ui header">차이</h5>
      <Delta :result="result"/>
    </div>
    <div class="column">
      <h5 class="ui header">기대 바디</h5>
      <pre>{{result.actual.body}}</pre>
    </div>
  </div>
</template>

<script>
import Delta from './Delta.vue'

export default {
  components: {Delta},
  props: {
    result: {
      type: Object,
      default: {
        expected: {},
        actual: {}
      }
    }
  }
}
</script>
