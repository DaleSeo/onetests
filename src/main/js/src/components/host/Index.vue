<template>
  <div class="ui container">
    <Title icon="cloud" title="Hosts" subTitle="호스트 관리"/>
    <div class="ui column grid">
      <div class="ten wide column">
        <ServiceDropdown v-model="serviceId" :fluid="true"/>
        <br/>
        <List :hosts="hosts" :host="host" @pick="pick"/>
      </div>
      <div class="six wide column">
        <Edit v-if="host" :host="host" @save="save" @cancel="cancel"/>
        <button v-if="!host && serviceId" class="ui fluid button" @click="add">
          <i class="plus icon"/>추가
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import hostSvc from '../../services/hostSvc'
import List from './List.vue'
import Edit from './Edit.vue'

export default {
  components: {List, Edit},
  data () {
    return {
      hosts: [],
      host: null,
      serviceId: ''
    }
  },
  created() {
    this.fetchHosts()
  },
  watch: {
    serviceId (val) {
      this.fetchHosts()
      this.host = null
    }
  },
  methods: {
    pick (host) {
      this.host = host
    },
    add () {
      console.log('#add')
      this.host = this.initHost()
    },
    save () {
      console.log('#save:', this.host)
      hostSvc.save(this.host)
        .then(_ => toastr.success('호스트가 저장되었습니다.'))
        .catch(err => toastr.error('호스트 저장 실패'))
        .then(_ => this.host = null)
        .then(this.fetchHosts)
    },
    cancel () {
      this.host = null
      this.fetchHosts()
    },
    fetchHosts () {
      hostSvc.list(this.serviceId)
        .then(hosts => this.hosts = hosts)
    },
    initHost () {
      return {
        serviceId: this.serviceId,
        title: '',
        baseUrl: '',
        environment: '',
        description: ''
      }
    }
  }
}
</script>
