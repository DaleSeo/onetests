<template>
  <div>
    <Item :key="service.id" :service="service" @pick="pick" v-for="service in services"/>
  </div>
</template>

<script>
import Item from './Item.vue'

export default {
  components: {Item},
  props: ['services', 'service'],
  methods: {
    pick (service) {
      this.$emit('pick', service)
    }
  }
}
</script>
