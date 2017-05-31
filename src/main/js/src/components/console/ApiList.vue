<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <i class="fa fa-list"/> <strong>API</strong> <small>({{apis.length}})</small>
    </div>
    <div class="panel-body">
      <select class="form-control" v-model="serviceId">
        <option value="">서비스를 선택하세요</option>
        <option :value="service.id" v-for="service in services">{{service.name}}</option>
      </select>
      <div style="height: 10px"/>
      <select class="form-control" v-model="hostId">
        <option value="">호스트를 선택하세요</option>
        <option :value="host.id" v-for="host in hosts">{{host.baseUrl}}</option>
      </select>
    </div>
    <ul class="list-group">
      <ApiItem
        v-for="api in apis"
        :key="api.id"
        :api="api"
        @pick="pick"
      />
    </ul>
  </div>
</template>

<script>
import _ from 'lodash'

import serviceSvc from '../../services/serviceSvc'
import hostSvc from '../../services/hostSvc'
import apiSvc from '../../services/apiSvc'

import ApiItem from './ApiItem.vue'

export default {
  components: {ApiItem},
  data () {
    return {
      serviceId: '',
      services: [],
      hostId: '',
      hosts: [],
      apis: []
    }
  },
  watch: {
    serviceId (newVal) {
      this.listHosts()
    },
    hostId (newVal) {
      this.listApis()
    },
  },
  created () {
    this.listServices()
    this.listHosts()
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
    listApis () {
      console.log('ApiList.vue#listApis()')
      apiSvc.list(this.serviceId)
        .then(apis => this.apis = apis)
        .catch(err => {
          console.error(err)
          toastr.error('API 목록 조회 실패')
        })
    },
    pick (api) {
      console.log('ApiList.vue#pick')
      let host = _.find(this.hosts, {id: this.hostId})
      api.url = host ? host.baseUrl + api.path : api.path
      this.$emit('pickApi', api)
    }
  }

}
</script>
