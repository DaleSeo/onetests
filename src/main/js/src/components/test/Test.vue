<template>
  <div>
    <TestDetail :suiteId="suiteId" :test="test" v-if="test" @unpick="unpick"/>
    <TestList :suiteId="suiteId" @pick="pick" v-show="!test"/>
  </div>
</template>

<script>
import TestDetail from './TestDetail.vue'
import TestList from './TestList.vue'

export default {
  props: ['suiteId'],
  components: {TestDetail, TestList},
  data() {
    return {
      test: null
    }
  },
  methods: {
    pick (test) {
      this.test = test
    },
    unpick () {
      this.test = null
    }
  }
}
</script>
