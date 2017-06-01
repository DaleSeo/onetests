<template>
  <div>
    <div class="ui fluid card">
      <div class="content">
        <div class="right floated meta">
          ({{cases.length}})
        </div>
        <div class="header">
          <small>테스트 케이스</small>
        </div>
        <div class="description">
          <select class="form-control" v-model="serviceId">
            <option value="">서비스를 선택하세요</option>
            <option :value="service.id" v-for="service in services">{{service.name}}</option>
          </select>
          <div style="height: 10px"></div>
          <select class="form-control" v-model="hostId">
            <option value="">호스트를 선택하세요</option>
            <option :value="host.id" v-for="host in hosts">{{host.baseUrl}}</option>
          </select>
        </div>
      </div>
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
import _ from 'lodash'

import serviceSvc from '../../services/serviceSvc'
import hostSvc from '../../services/hostSvc'
import caseSvc from '../../services/caseSvc'

import CaseItem from './CaseItem.vue'

export default {
  components: {CaseItem},
  data () {
    return {
      serviceId: '',
      services: [],
      hostId: '',
      hosts: [],
      cases: []
    }
  },
  watch: {
    serviceId (newVal) {
      this.listHosts()
      this.listCases()
    }
  },
  created () {
    this.listServices()
    this.listHosts()
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
    listHosts () {
      console.log('ApiList.vue#listHosts()')
      hostSvc.list(this.serviceId)
        .then(hosts => this.hosts = hosts)
        .catch(err => toastr.error('호스트 목록 조회 실패'))
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
      let host = _.find(this.hosts, {id: this.hostId})
      cas.url = host ? host.baseUrl + cas.path : cas.path
      this.$emit('pickApi', cas)
    }
  }

}
</script>
