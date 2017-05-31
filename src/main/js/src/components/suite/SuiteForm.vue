<template>
  <form @submit.prevent="createSuite" @reset.prevent="reset">
    <div class="row">
      <div class="col-md-12">
        <div class="form-group">
          <label for="title">검증 제목</label>
          <input id="title" type="text" class="form-control" required v-model="suite.title"/>
        </div>

        <div class="form-group">
          <label for="description">검증 설명</label>
          <textarea id="description" cols="3" class="form-control" required v-model="suite.description"/>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <div class="form-group">
          <label for="serviceId">서비스</label>
          <select id="serviceId" class="form-control" v-model="serviceId">
            <option value="">서비스를 선택하세요</option>
            <option :value="service.id" v-for="service in services">{{service.name}}</option>
          </select>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group">
          <label for="hostId">서비스</label>
          <select id="hostId" class="form-control" v-model="hostId">
            <option value="">호스트를 선택하세요</option>
            <option :value="host.id" v-for="host in hosts">{{host.baseUrl}}</option>
          </select>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <AvailableApiList :apis="availableApis" @pick="pickApi"/>
      </div>
      <div class="col-md-6">
        <SelectedApiList :apis="selectedApis" @unpick="unpickApi"/>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12 text-right">
        <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"/> 저장</button>
        <button type="reset" class="btn btn-sm btn-warning"><i class="fa fa-undo"/> 취소</button>
      </div>
    </div>

    <br/>
  </form>
</template>

<script>
import _ from 'lodash'

import suiteSvc from '../../services/suiteSvc'
import serviceSvc from '../../services/serviceSvc'
import hostSvc from '../../services/hostSvc'
import apiSvc from '../../services/apiSvc'

import AvailableApiList from './AvailableApiList.vue'
import SelectedApiList from './SelectedApiList.vue'

export default {
  components: { AvailableApiList, SelectedApiList },
  data () {
    return {
      suite: {
        title: '',
        description: ''
      },
      availableApis: [],
      selectedApis: [],
      serviceId: '',
      hostId: '',
      services: [],
      hosts: [],
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
      console.log('SuiteForm.vue#listServices()')
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
    },
    listHosts () {
      console.log('SuiteForm.vue#listHosts()')
      hostSvc.list(this.serviceId)
        .then(hosts => this.hosts = hosts)
        .catch(err => toastr.error('호스트 목록 조회 실패'))
    },
    listApis () {
      console.log('SuiteForm.vue#listApis()')
      apiSvc.list(this.serviceId)
        .then(apis => this.availableApis = apis)
        .catch(err => {
          console.error(err)
          toastr.error('API 목록 조회 실패')
        })
    },
    pickApi (api) {
      console.log('SuiteForm.vue#pickApi()')
      let host = _.find(this.hosts, {id: this.hostId})
      api.url = host ? host.baseUrl + api.path : api.path
      this.selectedApis.push(api)
    },
    unpickApi (api) {
      console.log('SuiteForm.vue#unpickApi()', api)
      this.selectedApis = this.selectedApis.filter(item => item.id !== api.id)
    },
    createSuite () {
      console.log('SuiteForm.vue#createSuite()')
      suiteSvc.create(this.suite, this.selectedApis)
        .then(id => window.location.href = '/suites/' + id)
        .catch(err => toastr.error('테스트 스위트 생성 실패'))
    }
  }
}
</script>
