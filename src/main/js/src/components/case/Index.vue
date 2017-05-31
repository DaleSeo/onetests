<template>
  <div class="container">
    <Title icon="cube" title="Test Case" subTitle="개별 검증"/>

    <div class="ui right aligned basic segment">
      <button class="ui mini primary labeled icon button" @click="create">
        <i class="plus icon"></i>
        등록
      </button>
    </div>

    <List :cases="cases"/>
  </div>
</template>
<script>
import caseSvc from '../../services/caseSvc'

import List from './List.vue'

export default {
  components: { List },
  data () {
    return {
      cases: [],
      id: ''
    }
  },
  created () {
    this.list()
  },
  methods: {
    list () {
      caseSvc.list(this.$route.query.suiteId)
        .then(cases => this.cases = cases)
    },
    create () {
      window.location.href = '/cases/new'
    }
  }
}
</script>
