<template>
  <i class="circular icon" :class="icon"/>
</template>

<script>
export default {
  props: ['result'],
  computed: {
    icon () {
      return this.result.passed ? 'thumbs up' : 'thumbs down'
    }
  }
}
</script>
