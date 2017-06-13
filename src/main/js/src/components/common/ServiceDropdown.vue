<template>
  <div id="serviceDropdown" class="ui selection dropdown" :class="{disabled: readonly}">
    <input type="hidden" :value="value" @change="change">
    <i class="dropdown icon"></i>
    <span class="default text">서비스를 선택하세요</span>
    <div class="menu">
      <div class="item" data-value="">서비스를 선택하세요</div>
      <div class="item" :data-value="service.id" v-for="service in services">{{service.name}}</div>
    </div>
  </div>
</template>

<script>
import serviceSvc from '../../services/serviceSvc'

export default {
  props: {
    value: String,
    readonly: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      services: []
    }
  },
  watch: {
    value (val) {
      $('#serviceDropdown.ui.dropdown').dropdown('set selected', val)
    }
  },
  created () {
    this.fetchServices()
  },
  mounted () {
    $('#serviceDropdown.ui.dropdown').dropdown()
  },
  methods: {
    fetchServices () {
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
    },
    change (event) {
      this.$emit('input', event.target.value)
    }
  }
}
</script>
