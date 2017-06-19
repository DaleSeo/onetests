<template>
  <div>
    <h5 class="ui top attached inverted small header">
      테스트 케이스 ({{cases.length}})
    </h5>
    <div class="ui attached segment">
      <ServiceDropdown v-model="serviceId" :fluid="true"/>
    </div>
    <div class="ui styled fluid accordion" v-show="cases.length > 0">
      <CaseItem
        v-for="cas in cases"
        :key="cas.id"
        :cas="cas"
        @pick="pick"
      />
    </div>
  </div>
</template>

<script>
import serviceSvc from '../../services/serviceSvc'
import caseSvc from '../../services/caseSvc'

import CaseItem from './CaseItem.vue'

export default {
  components: {CaseItem},
  data () {
    return {
      services: [],
      serviceId: '',
      cases: []
    }
  },
  watch: {
    serviceId (val) {
      this.listCases()
    }
  },
  created () {
    this.listServices()
  },
  mounted () {
    $('.ui.accordion').accordion()
  },
  methods: {
    listServices () {
      console.log('ApiList.vue#listServices()')
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
    },
    listCases () {
      console.log('ApiList.vue#listCases()')
      caseSvc.list(this.serviceId)
        .then(cases => this.cases = cases)
        .catch(err => {
          console.error(err)
          toastr.error('테스트 케이스 목록 조회 실패')
        })
    },
    pick (cas) {
      console.log('ApiList.vue#pick')
      this.$emit('pickApi', cas)
    }
  }

}
</script>
