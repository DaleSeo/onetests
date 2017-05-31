<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <i class="fa fa-list"/> <strong>선택 가능한 테스트 케이스</strong> <small>({{apis.length}})</small>
    </div>
    <ul class="list-group">
      <AvailableApiItem
        v-for="api in apis"
        :key="api.id"
        :api="api"
        @pick="pick"
      />
    </ul>
  </div>
</template>

<script>
import _ from 'lodash'

import AvailableApiItem from './AvailableApiItem.vue'

export default {
  props: ['apis'],
  components: {AvailableApiItem},
  methods: {
    pick (api) {
      console.log('#pick()', api)
      this.$emit('pick', api)
    }
  }

}
</script>
