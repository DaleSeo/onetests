<template>
  <div class="ui container">
    <Title icon="pagelines" title="Services" subTitle="서비스 관리"/>
    <div class="ui column grid">
      <div class="ten wide column">
        <List :services="services" :service="service" @pick="pick"/>
        <br/>
        <button class="ui fluid button" @click="add" v-if="service && service.id">
          <i class="plus icon"/>추가
        </button>
      </div>
      <div class="six wide column">
        <Edit v-if="service" :service="service" @save="save" @cancel="cancel"/>
      </div>
    </div>
  </div>
</template>

<script>
import serviceSvc from '../../services/serviceSvc'
import List from './List.vue'
import Edit from './Edit.vue'

export default {
  components: {List, Edit},
  data () {
    return {
      service: null,
      services: []
    }
  },
  created () {
    this.fetchServices()
  },
  methods: {
    pick (service) {
      this.service = service
    },
    add () {
      console.log('#add')
      this.service = this.initService()
      this.services.push(this.service)
    },
    save () {
      console.log('#save:', this.service)
      serviceSvc.save(this.service)
        .then(_ => toastr.success('서비스가 저장되었습니다.'))
        .catch(err => toastr.error('서비스 저장 실패'))
        .then(_ => this.service = null)
        .then(this.fetchServices)
    },
    cancel () {
      this.service = null
      this.fetchServices()
    },
    fetchServices () {
      console.log('ServiceList.vue#list()')
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('목록 조회 실패'))
    },
    initService () {
      return {
        code: '',
        name: '',
        description: ''
      }
    },
  }
}
</script>
