<template>
  <div class="ui equal width stackable grid">
    <div class="row">
      <div class="column">
        <AvailableCases
          :serviceId="suite.serviceId"
          @pick="pick"
        />
      </div>
      <div class="column">
        <SelectedCases
          :cases="cases"
        />
      </div>
    </div>
    <div class="row">
      <div class="right aligned column">
        <button class="ui labeled icon button" @click="back">
          <i class="step backward icon"/>이전
        </button>
        <button class="ui labeled icon button" @click="next">
          <i class="step forward icon"/>다음
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import suiteSvc from '../../services/suiteSvc'
import AvailableCases from './AvailableCases.vue'
import SelectedCases from './SelectedCases.vue'

export default {
  components: {AvailableCases, SelectedCases},
  props: ['suite'],
  data () {
    return {
      cases: []
    }
  },
  methods: {
    pick (cas) {
      console.log('#pick:', cas)
      this.cases.push(cas)
      this.suite.caseIds.push(cas.id)
    },
    back () {
      this.$emit('changeStep', 0)
    },
    next () {
      console.log('#createSuite')
      suiteSvc.create(this.suite)
        .then(id => {
          window.location.href = '/suites/' + id
          // this.suite.id = id
          // this.$emit('changeStep', 2)
        })
        .catch(err => toastr.error('테스트 스위트 생성 실패'))
    }
    // pickApi (api) {
    //   console.log('SuiteForm.vue#pickApi()')
    //   let host = _.find(this.hosts, {id: this.hostId})
    //   api.url = host ? host.baseUrl + api.path : api.path
    //   this.selectedApis.push(api)
    // },
    // unpickApi (api) {
    //   console.log('SuiteForm.vue#unpickApi()', api)
    //   this.selectedApis = this.selectedApis.filter(item => item.id !== api.id)
    // }
  }
}
</script>
