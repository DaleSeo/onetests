<template>
  <div class="ui list">
    <ResultItem :key="result.id" :result="result" v-for="result in results"/>
  </div>
</template>

<script>
import ResultItem from './ResultItem.vue'

export default {
  components: {ResultItem},
  props: ['results']
}
</script>
