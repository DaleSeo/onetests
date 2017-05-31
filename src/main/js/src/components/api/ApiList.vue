<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <strong>API 목록</strong> <small>({{apis.length}})</small>
      <button class="btn btn-xs btn-primary pull-right" @click="create"><i class="fa fa-pencil-square-o"/> 등록</button>
    </div>
    <ul class="list-group">
      <ApiItem
        v-for="api in apis"
        :key="api.id"
        :api="api"
        :activeApi="activeApi"
        @detail="detail"
      />
    </ul>
  </div>
</template>

<script>
import ApiItem from './ApiItem.vue'

export default {
  props: ['apis', 'activeApi'],
  components: {ApiItem},
  methods: {
    create () {
      this.$emit('create')
    },
    detail (api) {
      this.$emit('detail', api)
    }
  }
}
</script>
