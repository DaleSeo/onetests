<template>
  <div id="serviceFilter" class="ui pointing dropdown labeled icon button">
    <input type="hidden" @change="change">
    <i class="filter icon"/>
    <span class="text">서비스 필터</span>
    <div class="menu">
      <div class="item" data-value="">서비스 필터</div>
      <div class="item" :data-value="service.id" v-for="service in services">{{service.name}}</div>
    </div>
  </div>
</template>

<script>
import serviceSvc from '../../services/serviceSvc'

export default {
  props: ['value'],
  data () {
    return {
      services: []
    }
  },
  created () {
    this.fetch()
  },
  mounted () {
    $('#serviceFilter.ui.dropdown').dropdown()
    let id = this.value ? this.value.id : ''
    $('#serviceFilter.ui.dropdown').dropdown('set selected', id)
  },
  methods: {
    fetch () {
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
    },
    change (event) {
      let service = this.services.find(svc => svc.id === event.target.value)
      this.$emit('input', service)
    }
  }
}
</script>
