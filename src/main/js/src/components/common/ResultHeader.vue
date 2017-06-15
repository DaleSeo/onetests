<template>
  <h5 class="ui center aligned icon header">
    <i class="circular icon" :class="icon"/>
    {{text}}
  </h5>
</template>

<script>
export default {
  props: ['result'],
  computed: {
    text () {
      return this.result.passed ? 'PASS' : 'FAIL'
    },
    icon () {
      return this.result.passed ? 'thumbs up' : 'thumbs down'
    }
  }
}
</script>
