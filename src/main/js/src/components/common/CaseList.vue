<template>
  <div class="ui styled fluid accordion" v-show="cases.length > 0">
    <CaseItem
      v-for="cas in cases"
      :key="cas.id"
      :cas="cas"
      @pick="pick"
    />
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'

export default {
  props: {
    cases: {
      type: Array,
      default: []
    }
  },
  // mounted () {
  //   this.accordion()
  // },
  updated () {
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
