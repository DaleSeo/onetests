<template>
  <div class="container">
    <component :is="view"/>
  </div>
</template>

<script>
import Services from '../service/Index.vue'
import Hosts from '../host/Index.vue'
import Settings from '../settings/Index.vue'
import Variables from '../variable/Index.vue'

export default {
  props: ['category'],
  computed: {
    view () {
      switch (this.category) {
        case 'services': return Services
        case 'hosts': return Hosts
        case 'settings': return Settings
        case 'variables': return Variables
      }
    }
  }
}
</script>

<style>
</style>
