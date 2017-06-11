<template>
  <div class="ui divided items">
    <ResultItem
      :key="result.id"
      :result="result"
      @view="view"
      v-for="result in results"
    />
  </div>
</template>

<script>
import ResultItem from './ResultItem.vue'

export default {
  components: {ResultItem},
  props: ['results'],
  methods: {
    view (id) {
      this.$emit('view', id)
    }
  }
}
</script>
