<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <strong>테스트 아이템</strong> <small>({{tests.length}})</small>
    </div>
    <ul class="list-group">
      <TestItem
        :test="test"
        :suiteId="suiteId"
        :key="test['.key']"
        @pick="pick"
        v-for="test in tests"
      />
    </ul>
  </div>
</template>

<script>
import TestItem from './TestItem.vue'

import db from '../../services/database'

export default {
  props: ['suiteId'],
  components: {TestItem},
  firebase () {
    return {
      tests: db.ref('tests/').child(this.suiteId)
    }
  },
  methods: {
    pick (test) {
      this.$emit('pick', test)
    }
  }
}
</script>
