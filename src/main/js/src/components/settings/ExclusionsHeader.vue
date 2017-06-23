<template>
  <ListForm
    title="헤더 전역 예외"
    :items="exclusions"
    :loading="loading"
    @add="add"
    @del="del"
  />
</template>

<script>
import ListForm from '../common/ListForm.vue'

export default {
  components: {ListForm},
  props: {
    exclusions: Array,
    loading: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    add (exclusion) {
      this.exclusions.push(exclusion)
      this.$emit('save')
    },
    del (exclusion) {
      for (let i in this.exclusions) {
        if (this.exclusions[i] === exclusion) {
          this.exclusions.splice(i, 1)
          break
        }
      }
      this.$emit('save')
    }
  }
}
</script>
