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
    <div class="ui accordion">
      <div class="title">
        <i class="dropdown icon"></i>
        What is a dog?
      </div>
      <div class="content">
        <p>A dog is a type of domesticated animal. Known for its loyalty and faithfulness, it can be found as a welcome guest in many households across the world.</p>
      </div>
      <div class="title">
        <i class="dropdown icon"></i>
        What kinds of dogs are there?
      </div>
      <div class="content">
        <p>There are many breeds of dogs. Each breed varies in size and temperament. Owners often select a breed of dog that they find to be compatible with their own lifestyle and desires from a companion.</p>
      </div>
      <div class="title">
        <i class="dropdown icon"></i>
        How do you acquire a dog?
      </div>
      <div class="content">
        <p>Three common ways for a prospective owner to acquire a dog is from pet shops, private owners, or shelters.</p>
        <p>A pet shop may be the most convenient way to buy a dog. Buying a dog from a private owner allows you to assess the pedigree and upbringing of your dog before choosing to take it home. Lastly, finding your dog from a shelter, helps give a good home to a dog who may not find one so readily.</p>
      </div>
    </div>
    <div>
      <ul class="list-group">
        <CaseItem
          v-for="cas in cases"
          :key="cas.id"
          :cas="cas"
          @pick="pick"
        />
      </ul>
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
    console.log('===========================')
    console.log($('.ui.accordion').accordion())
    $('.ui.accordion').accordion();
    console.log('<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
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
    pick (api) {
      console.log('ApiList.vue#pick')
      let host = _.find(this.hosts, {id: this.hostId})
      api.url = host ? host.baseUrl + api.path : api.path
      this.$emit('pickApi', api)
    }
  }

}
</script>
