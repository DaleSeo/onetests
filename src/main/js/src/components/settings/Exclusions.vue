<template>
  <div class="ui two column grid">
    <div class="column">
      <ExclusionsHeader :exclusions="setting.headerExclusions" :loading="loading" @save="save"/>
    </div>
    <div class="column">
      <ExclusionsBody :exclusions="setting.bodyExclusions" :loading="loading" @save="save"/>
    </div>
  </div>
</template>

<script>
import ExclusionsHeader from './ExclusionsHeader.vue'
import ExclusionsBody from './ExclusionsBody.vue'

export default {
  components: {ExclusionsHeader, ExclusionsBody},
  data () {
    return {
      setting: {
        headerExclusions: [],
        bodyExclusions: [],
      },
      loading: false
    }
  },
  created () {
    this.fetch()
  },
  methods: {
    fetch() {
      this.loading = true
      return this.$http.get('/api/settings/594c9ddc0cb6faf0257e2fe0')
        .then(res => res.body)
        .then(setting => this.setting = setting)
        .catch(err => toastr.error('환경 설정 정보 조회 실패'))
        .then(_ => this.loading = false)
    },
    save () {
      return this.$http.patch('/api/settings/594c9ddc0cb6faf0257e2fe0', this.setting)
        .then(_ => toastr.success('환경 정보가 저장되었습니다.'))
        .catch(err => toastr.error('환경 설정 정보 조회 실패'))
        .then(this.fetch)
    }
  }
}
</script>
