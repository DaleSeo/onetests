<template>
  <div class="container">
    <component :is="view"/>
  </div>
</template>

<script>
import Services from '../service/Index.vue'
import Hosts from '../host/Index.vue'

export default {
  components: {Services, Hosts},
  props: ['category'],
  computed: {
    view () {
      switch (this.category) {
        case 'services': return Services
        case 'hosts': return Hosts
      }
    }
  }
}
</script>

<style>
</style>
