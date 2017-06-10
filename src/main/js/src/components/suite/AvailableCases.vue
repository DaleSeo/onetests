<template>
  <div id="caseList">
    <h5 class="ui top attached inverted small header">
      선택 가능한 테스트 케이스 ({{cases.length}})
    </h5>
    <CaseList :cases="cases" @pick="pick"/>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'

export default {
  props: ['serviceId'],
  data () {
    return {
      cases: []
    }
  },
  created () {
    this.fetchCases()
  },
  updated () {
    console.log('#caseList#updated')
    this.accordion()
  },
  methods: {
    fetchCases () {
      caseSvc.list(this.serviceId)
        .then(cases => this.cases = cases)
        .catch(err => toastr.error('테스트 케이스 목록 조회 실패'))
    },
    accordion () {
      $('#caseList .ui.accordion').accordion()
    },
    pick (cas) {
      this.$emit('pick', cas)
    }
  }

}
</script>
