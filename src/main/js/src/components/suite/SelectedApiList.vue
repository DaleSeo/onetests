<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <i class="fa fa-list"/> <strong>선택한 테스트 케이스</strong> <small>({{apis.length}})</small>
    </div>
    <ul class="list-group">
      <SelectedApiItem
        v-for="api in apis"
        :key="api.id"
        :api="api"
        @unpick="unpick"
      />
    </ul>
  </div>
</template>

<script>
import _ from 'lodash'

import serviceSvc from '../../services/serviceSvc'
import hostSvc from '../../services/hostSvc'
import apiSvc from '../../services/apiSvc'

import SelectedApiItem from './SelectedApiItem.vue'

export default {
  props: ["apis"],
  components: {SelectedApiItem},
  methods: {
    unpick (api) {
      this.$emit('unpick', api)
    }
  }
}
</script>
