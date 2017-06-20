<template>
  <div class="ui two column internally celled grid">
    <div class="column">
      <h5 class="ui header">기대 상태</h5>
      {{result.expected.statusCode + ' ' + result.expected.statusMessage}}
    </div>
    <div class="column">
      <h5 class="ui header">실제 상태</h5>
      {{result.actual.statusCode + ' ' + result.actual.statusMessage}}
    </div>
  </div>
</template>

<script>
export default {
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
