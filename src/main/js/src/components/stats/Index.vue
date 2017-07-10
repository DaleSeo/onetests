<template>
  <div class="ui container">
    <Title icon="dashboard" title="Stats" subTitle="통계"/>
    <div class="basic segment" :class="{loading}">
      <div class="ui equal width grid">
        <div class="column">
          <div class="ui statistic">
            <div class="label">
              테스트 실행 횟수
            </div>
            <div class="value">
              {{stats.numCalls}}
            </div>
          </div>
        </div>
        <div class="column">
          <div class="ui statistic">
            <div class="label">
              테스트 케이스 개수
            </div>
            <div class="value">
              {{stats.numCases}}
            </div>
          </div>
        </div>
        <div class="column">
          <div class="ui statistic">
            <div class="label">
              테스트 스위트 개수
            </div>
            <div class="value">
              {{stats.numSuites}}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      stats: {
        numCalls: 0,
        numCases: 0,
        numSuites: 0,
      },
      loading: false
    }
  },
  created () {
    this.fetch()
  },
  methods: {
    fetch () {
      this.loading = true
      this.$http.get('/api/stats')
        .then(res => res.body)
        .then(stats => this.stats = stats)
        .catch(err => toastr.error('통계 정보 조회 실패'))
        .then(_ => this.loading = false)
    }
  }
}
</script>
