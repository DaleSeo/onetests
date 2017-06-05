<template>
  <div>
    <h5 class="ui top attached inverted small header">
      <i class="arrow circle right icon"/>
      <div class="content">
        요청
      </div>
    </h5>
    <form class="ui attached form segment" @submit.prevent @keyup.enter="callApi">
      <div class="fields">
        <div class="sixteen wide field">
          <select class="ui fluid dropdown" v-model="request.host">
            <option value="">호스트</option>
            <option :value="host.baseUrl" v-for="host in hosts">{{host.baseUrl}}</option>
          </select>
        </div>
      </div>
      <div class="fields">
        <div class="four wide field">
          <select class="ui fluid dropdown" v-model="request.method" required>
            <option v-for="method in methods">{{method}}</option>
          </select>
        </div>
        <div class="twelve wide field">
          <input type="text" v-model="request.path" required/>
        </div>
      </div>
      <div id="reqTab">
        <div class="ui tabular menu">
          <a class="item" data-tab="req-first">쿼리</a>
          <a class="item" data-tab="req-second">헤더</a>
          <a class="item active" data-tab="req-third">바디</a>
        </div>
        <div class="ui tab" data-tab="req-first">
          <Entries :entries="request.queries"/>
        </div>
        <div class="ui tab" data-tab="req-second">
          <Entries :entries="request.headers"/>
        </div>
        <div class="ui tab active" data-tab="req-third">
          <textarea rows="5" v-model="request.body"/>
        </div>
      </div>
      <br/>
      <div style="text-align: right">
          <button type="button" class="ui labeled icon small button" @click="callApi">
            <i class="play icon"/> 호출
          </button>
          <button type="button" class="ui labeled icon small button" @click="cancel">
            <i class="undo icon"/> 취소
          </button>
          <button type="button" class="ui labeled icon small button" @click="curl">
            <i class="code icon"/> CURL
          </button>
      </div>
    </form>
  </div>
</template>

<script>
import hostSvc from '../../services/hostSvc'

import Entries from '../common/Entries.vue'

export default {
  components: {Entries},
  props: ['request', 'serviceId'],
  data () {
    return {
      tab: 'body',
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD'],
      hosts: []
    }
  },
  watch: {
    serviceId (val) {
      console.log('serviceId: ', this.serviceId)
      this.listHosts()
    }
  },
  created () {
    this.listHosts()
  },
  mounted () {
    $('#reqTab .menu .item').tab({
      context: $('#reqTab')
    })
  },
  methods: {
    callApi () {
      this.$emit('callApi')
    },
    listHosts () {
      console.log('ApiList.vue#listHosts()', this.serviceId)
      hostSvc.list(this.serviceId)
        .then(hosts => this.hosts = hosts)
        .catch(err => toastr.error('호스트 목록 조회 실패'))
    },
    callApi () {
      this.$emit('callApi')
    },
    cancel () {
      this.$emit('cancel')
    },
    curl () {
      $('#modalCurl').modal('show')
    }
  }
}
</script>

<style scoped>
.nav {
  margin-bottom: 15px;
}
</style>
