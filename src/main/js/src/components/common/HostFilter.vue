<template>
  <div id="hostFilter" class="ui pointing dropdown labeled icon button" :class="{disabled}">
    <input type="hidden" @change="change">
    <i class="filter icon"/>
    <span class="text">호스트 필터</span>
    <div class="menu">
      <div class="item" data-value="">호스트 필터</div>
      <div class="item" :data-value="host.id" v-for="host in hosts">{{host.baseUrl}}</div>
    </div>
  </div>
</template>

<script>
import hostSvc from '../../services/hostSvc'

export default {
  props: ['value', 'serviceId'],
  data () {
    return {
      hosts: [],
      disabled: true
    }
  },
  watch: {
    serviceId (val) {
      this.disabled = val ? false : true
      $('#hostFilter.ui.dropdown').dropdown('restore defaults')

      let id = this.value ? this.value.id : ''
      this.fetch()
    }
  },
  mounted () {
    $('#hostFilter.ui.dropdown').dropdown()
    let id = this.value ? this.value.id : ''
    $('#hostFilter.ui.dropdown').dropdown('set selected', id)
  },
  methods: {
    fetch () {
      if (!this.serviceId) return
      hostSvc.list(this.serviceId)
        .then(hosts => this.hosts = hosts)
        .catch(err => toastr.error('호스트 목록 조회 실패'))
    },
    change (event) {
      let host = this.hosts.find(host => host.id === event.target.value)
      this.$emit('input', host)
    }
  }
}
</script>
