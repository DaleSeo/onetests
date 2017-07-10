<template>
  <div class="ui equal width stackable internally celled grid">
    <div class="column">
      <h3 class="ui center aligned icon header">
        <i class="toggle right icon"/>
        테스트 실행 횟수
      </h3>
      <div class="ui statistic">
        <div class="value">
          {{stats.numCalls}}
        </div>
        <div class="label">
          Tests
        </div>
      </div>
    </div>
    <div class="column">
      <h3 class="ui center aligned icon header">
        <i class="cube icon"/>
        테스트 케이스 개수
      </h3>
      <div class="ui statistic">
        <div class="value">
          {{stats.numCases}}
        </div>
        <div class="label">
          Cases
        </div>
      </div>
    </div>
    <div class="column">
      <h3 class="ui center aligned icon header">
        <i class="cubes icon"/>
        테스트 스위트 개수
      </h3>
      <div class="ui statistic">
        <div class="value">
          {{stats.numSuites}}
        </div>
        <div class="label">
          Suites
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

<style scoped>
h3.ui.header {
  margin: 0;
}
</style>
