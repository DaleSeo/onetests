<template>
  <div>
    <Item :host="host" @pick="pick" v-for="host in hosts"/>
  </div>
</template>

<script>
import Item from './Item.vue'

export default {
  components: {Item},
  props: ['hosts', 'host'],
  methods: {
    pick (host) {
      this.$emit('pick', host)
    }
  }
}
</script>
