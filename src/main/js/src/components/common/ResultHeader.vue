<template>
  <i class="icon" :class="icon"/>
</template>

<script>
export default {
  props: ['result'],
  computed: {
    icon () {
      return this.result.passed ? 'green smile' : 'red frown'
    }
  }
}
</script>
