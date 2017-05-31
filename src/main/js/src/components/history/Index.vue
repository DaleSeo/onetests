<template>
  <div class="ui container">
    <Title icon="history" title="History" subTitle="호출 이력"/>
    <List :calls="calls"/>
  </div>
</template>
<script>
import callSvc from '../../services/callSvc'

import List from './List.vue'

export default {
  components: { List },
  data () {
    return {
      calls: [],
      id: ''
    }
  },
  created () {
    this.list()
  },
  methods: {
    list () {
      callSvc.list(this.$route.query.suiteId)
        .then(calls => this.calls = calls)
    }
  }
}
</script>
