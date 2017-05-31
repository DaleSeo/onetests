<template>
  <div class="center-block" :style="{width: '500px'}">
    <div class="panel panel-default">
      <div class="panel-heading">
        API 서비스 목록 <small>({{services.length}})</small>
        <button class="btn btn-xs btn-primary pull-right" @click="showForm"><i class="fa fa-plus"/></button>
      </div>
      <ul class="list-group">
        <router-link
          class="list-group-item"
          :to="`/services/${service.id}`"
          :key="service.id"
          v-for="service in services">
          <div class="text-center"><strong>{{service.name}}</strong></div>
        </router-link>
      </ul>
    </div>
    <ServiceForm v-if="form" :service="service" @add="add" @cancel="cancel"/>
  </div>
</template>

<script>
import ServiceForm from './ServiceForm.vue'

import serviceSvc from '../../services/serviceSvc'

export default {
  components: {ServiceForm},
  data () {
    return {
      form: false,
      service: this.initService(),
      services: []
    }
  },
  created () {
    this.list()
  },
  methods: {
    initService () {
      return {
        code: '',
        name: '',
        description: ''
      }
    },
    showForm () {
      this.form = true
    },
    add () {
      console.log('# add')
      serviceSvc.create(this.service)
        .then(_ => {
          toastr.success('Created')
          this.service = this.initService()
          this.list()
        })
    },
    cancel () {
      this.form = false
      this.service = this.initService()
    },
    list () {
      console.log('ServiceList.vue#list()')
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('목록 조회 실패'))
    }
  }
}
</script>
