<template>
  <div id="hostDropdown" class="ui selection dropdown" :class="{disabled: readonly}">
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
  props: {
    value: String,
    serviceId: String,
    readonly: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      hosts: []
    }
  },
  watch: {
    serviceId (val) {
      console.log('#serviceId is updated')
      console.log(val + '::' + this.value)
      this.refresh(val, this.value)
      // if (val) {
      //   this.fetchHosts(val)
      // } else {
      //   this.hosts = []
      // }
    },
    value (val) {
      console.log('#hostId is updated')
      console.log(this.serviceId + '::' + val)
      this.refresh(this.serviceId, val)
      // $('#hostDropdown.ui.dropdown').dropdown('set selected', val)
    }
  },
  mounted () {
    $('#hostDropdown.ui.dropdown').dropdown()
  },
  methods: {
    refresh (serviceId, hostId) {
      if (serviceId) {
        this.fetchHosts(serviceId)
          .then(_ => {
            if (hostId) {
              $('#hostDropdown.ui.dropdown').dropdown('set selected', this.hostId)
            } else {
              $('#hostDropdown.ui.dropdown').dropdown('restore defaults')
            }
          })
      } else {
        this.hosts = []
      }
    },
    fetchHosts (serviceId) {
      return hostSvc.list(serviceId)
        .then(hosts => this.hosts = hosts)
        .catch(err => toastr.error('호스트 목록 조회 실패'))
    },
    change (event) {
      this.$emit('input', event.target.value)
    }
  }
}
</script>
