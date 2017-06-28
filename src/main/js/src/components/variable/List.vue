<template>
  <div>
    <Item :key="environment.id" :environment="environment" @pick="$emit('pick', environment)" v-for="environment in environments"/>
  </div>
</template>

<script>
import Item from './Item.vue'

export default {
  components: {Item},
  props: ['environments']
}
</script>
