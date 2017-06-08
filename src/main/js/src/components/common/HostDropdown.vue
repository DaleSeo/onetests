<template>
  <div id="hostDropdown" class="ui selection dropdown" :class="{disabled}">
    <input type="hidden" :value="value" @change="change">
    <i class="dropdown icon"></i>
    <span class="default text">호스트를 선택하세요</span>
    <div class="menu">
      <div class="item" data-value="">호스트를 선택하세요</div>
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
      $('#hostDropdown.ui.dropdown').dropdown('restore defaults')
      if (val) {
        this.fetchHosts(val)
        this.disabled = false
      } else {
        this.hosts = []
        this.disabled = true
      }
    }
  },
  mounted () {
    $('#hostDropdown.ui.dropdown').dropdown()
  },
  methods: {
    fetchHosts (serviceId) {
      hostSvc.list(serviceId)
        .then(hosts => this.hosts = hosts)
        .catch(err => toastr.error('호스트 목록 조회 실패'))
    },
    change (event) {
      this.$emit('input', event.target.value)
    }
  }
}
</script>
