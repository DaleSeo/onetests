<template>
  <ListForm
    variant="primary"
    title="헤더 전역 예외"
    :items="exclusions"
    @add="add"
    @del="del"
  />
</template>

<script>
import ListForm from '../common/ListForm.vue'

import db from '../../services/database'

export default {
  components: {ListForm},
  computed: {
    exclusions () {
      return this.exclustionsObj['.value'] || []
    }
  },
  firebase () {
    return {
      exclustionsObj: {
        source: db.ref('settings/headersExclusions'),
        asObject: true
      }
    }
  },
  methods: {
    add (exclusion) {
      this.exclusions.push(exclusion)
      this.$firebaseRefs.exclustionsObj.set(this.exclusions)
    },
    del (exclusion) {
      let exclusions = this.exclusions.filter(e => e !== exclusion)
      this.$firebaseRefs.exclustionsObj.set(exclusions)
    }
  }
}
</script>
