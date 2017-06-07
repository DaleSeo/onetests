<template>
  <form class="ui small form segment" @submit.prevent="createSuite" @reset.prevent="reset">
    <div class="field required">
      <label for="title">제목</label>
      <input id="title" type="text" required v-model="suite.title"/>
    </div>

    <div class="field">
      <label for="description">설명</label>
      <textarea id="description" cols="3" v-model="suite.description"/>
    </div>

    <div class="fields">
      <div class="eight wide field">
        <label for="serviceId">서비스</label>
        <select id="serviceId" v-model="serviceId">
          <option value="">서비스를 선택하세요</option>
          <option :value="service.id" v-for="service in services">{{service.name}}</option>
        </select>
      </div>
      <div class="eight wide field">
        <label for="hostId">호스트</label>
        <select id="hostId" v-model="hostId">
          <option value="">호스트를 선택하세요</option>
          <option :value="host.id" v-for="host in hosts">{{host.baseUrl}}</option>
        </select>
      </div>
    </div>

    <div class="fields">
      <div class="eight wide field">
        <AvailableApiList :apis="availableApis" @pick="pickApi"/>
      </div>
      <div class="eight wide field">
        <SelectedApiList :apis="selectedApis" @unpick="unpickApi"/>
      </div>
    </div>

    <div class="right aligned field">
      <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"/> 저장</button>
      <button type="reset" class="btn btn-sm btn-warning"><i class="fa fa-undo"/> 취소</button>
    </div>
  </form>
</template>

<script>
import _ from 'lodash'

import suiteSvc from '../../services/suiteSvc'
import serviceSvc from '../../services/serviceSvc'
import hostSvc from '../../services/hostSvc'
import caseSvc from '../../services/caseSvc'

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
      caseSvc.list(this.serviceId)
        .then(cases => this.availableApis = cases)
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
