<template>
  <div class="container">
    <ServiceList/>
  </div>
</template>

<script>
import ServiceList from './service/ServiceList.vue'

export default {
  components: {ServiceList},
  methods: {
    pick (id) {
      console.log('#pick:', id)
      this.id = id
    }
  }
}
</script>

<style>
</style>
