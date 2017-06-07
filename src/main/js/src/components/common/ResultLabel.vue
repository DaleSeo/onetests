<template>
  <div class="ui tag label" :class="color">
    <i class="icon" :class="icon"/>
    {{text}}
  </div>
</template>

<script>
export default {
  props: ['result'],
  computed: {
    text () {
      if (isResultUnavailable(this.result)) return 'NONE'
      return this.result.passed ? 'PASS' : 'FAIL'
    },
    color () {
      if (isResultUnavailable(this.result)) return 'yellow'
      return this.result.passed ? 'green' : 'red'
    },
    icon () {
      if (isResultUnavailable(this.result)) return 'meh'
      return this.result.passed ? 'smile' : 'frown'
    }
  }
}

function isResultUnavailable (result) {
  if (!result) return true
  if (!result.passed === undefined || !result.passed === null) return true
  return false
}
</script>
