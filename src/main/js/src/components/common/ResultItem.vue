<template>
  <div class="ui left labeled button">
    <div class="ui basic right pointing label" :class="color">
      {{title}}
    </div>
    <div class="ui button" :class="color">
      <i class="icon" :class="icon"/>
      {{text}}
    </div>
  </div>
</template>

<script>
export default {
  props: ['title', 'result'],
  computed: {
    text () {
      return this.result ? 'GOOD' : 'BAD'
    },
    color () {
      return this.result ? 'green' : 'red'
    },
    icon () {
      return this.result ? 'thumbs up' : 'thumbs down'
    }
  }
}
</script>
