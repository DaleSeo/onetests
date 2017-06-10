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
          :cases="suite.cases"
        />
      </div>
    </div>
    <div class="row">
      <div class="right aligned column">
        <button type="button" class="ui labeled icon button">
          <i class="save icon"/>저장
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import AvailableCases from './AvailableCases.vue'
import SelectedCases from './SelectedCases.vue'

export default {
  components: {AvailableCases, SelectedCases},
  props: ['suite'],
  methods: {
    pick (cas) {
      console.log('#pick:', cas)
      this.suite.cases.push(cas)
    }
  }
  // methods: {
  //   listServices () {
  //     console.log('SuiteForm.vue#listServices()')
  //     serviceSvc.list()
  //       .then(services => this.services = services)
  //       .catch(err => toastr.error('서비스 목록 조회 실패'))
  //   },
  //   listHosts () {
  //     console.log('SuiteForm.vue#listHosts()')
  //     hostSvc.list(this.serviceId)
  //       .then(hosts => this.hosts = hosts)
  //       .catch(err => toastr.error('호스트 목록 조회 실패'))
  //   },
  //   listApis () {
  //     console.log('SuiteForm.vue#listApis()')
  //     caseSvc.list(this.serviceId)
  //       .then(cases => this.availableApis = cases)
  //       .catch(err => {
  //         console.error(err)
  //         toastr.error('API 목록 조회 실패')
  //       })
  //   },
  //   pickApi (api) {
  //     console.log('SuiteForm.vue#pickApi()')
  //     let host = _.find(this.hosts, {id: this.hostId})
  //     api.url = host ? host.baseUrl + api.path : api.path
  //     this.selectedApis.push(api)
  //   },
  //   unpickApi (api) {
  //     console.log('SuiteForm.vue#unpickApi()', api)
  //     this.selectedApis = this.selectedApis.filter(item => item.id !== api.id)
  //   },
  //   createSuite () {
  //     console.log('SuiteForm.vue#createSuite()')
  //     suiteSvc.create(this.suite, this.selectedApis)
  //       .then(id => window.location.href = '/suites/' + id)
  //       .catch(err => toastr.error('테스트 스위트 생성 실패'))
  //   }
  // }
}
</script>
