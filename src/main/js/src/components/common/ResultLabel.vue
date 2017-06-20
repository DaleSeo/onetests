<template>
  <div class="ui left labeled mini button">
    <div class="ui basic right pointing mini label" :class="color">
      {{title}}
    </div>
    <div class="ui mini button" :class="color" @click="click">
      <i class="mini icon" :class="icon"/>
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
  },
  methods: {
    click () {
      this.$emit('click')
    }
  }
}
</script>
