<template>
  <div class="ui container">
    <Title icon="cube" title="Test Case" subTitle="개별 검증"/>
    <form class="ui small form segment" :class="{loading}" @submit.prevent="saveCase">
      <div class="field required">
        <label>제목</label>
        <input type="text" required v-model="cas.title">
      </div>

      <div class="field">
        <label>설명</label>
        <textarea v-model="cas.description"/>
      </div>

      <div class="fields">
        <div class="four wide required field">
          <label>서비스</label>
          <select class="ui fluid dropdown" v-model="cas.serviceId" required>
            <option :value="service.id" v-for="service in services">{{service.name}}</option>
          </select>
        </div>
        <div class="twelve wide required field">
          <label>호스트</label>
          <select class="ui fluid dropdown" v-model="cas.request.host" required>
            <option :value="host.baseUrl" v-for="host in hosts">{{host.baseUrl}}</option>
          </select>
        </div>
      </div>

      <h5 class="ui horizontal divider header">
        요청 상세
      </h5>

      <div class="fields">
        <div class="four wide required field">
          <label>메소드</label>
          <select class="ui fluid dropdown" v-model="cas.request.method" required>
            <option v-for="method in methods">{{method}}</option>
          </select>
        </div>
        <div class="twelve wide required field">
          <label>패스</label>
          <input type="text" required v-model="cas.request.path">
        </div>
      </div>

      <div class="field">
        <label>쿼리 파라미터</label>
        <Entries :entries="cas.request.queries"/>
      </div>

      <div class="field">
        <label>헤더</label>
        <Entries :entries="cas.request.headers"/>
      </div>

      <div class="field">
        <label>바디</label>
        <textarea v-model="cas.request.body"/>
      </div>

      <h5 class="ui horizontal divider header">
        기대 응답
      </h5>

      <div class="field">
        <label>상태 코드</label>
        <input type="text" v-model="cas.response.statusCode">
      </div>

      <div class="field">
        <label>헤더</label>
        <Entries :entries="cas.response.headers"/>
      </div>

      <div class="field">
        <label>바디</label>
        <textarea v-model="cas.response.body"/>
      </div>

      <button type="submit" class="ui primary button" :class="{loading: inProgress}" tabindex="0">저장</button>
      <button type="reset" class="ui secondary button" tabindex="0">취소</button>
    </form>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import serviceSvc from '../../services/serviceSvc'
import hostSvc from '../../services/hostSvc'
import callSvc from '../../services/callSvc'

import Entries from '../common/Entries.vue'

export default {
  components: { Entries },
  props: ['id'],
  data () {
    return {
      cas: this.initCase(),
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD'],
      services: [],
      hosts: [],
      callId: this.$route.query.callId || '',
      loading: false,
      inProgress: false
    }
  },
  created () {
    this.listHosts()
      .then(this.listServices)
      .then(_ => {
        if (this.id) {
          this.fetchCase ()
        }
        if (this.callId) {
          this.fetchCall ()
        }
      })
  },
  watch: {
    'cas.serviceId': function (newVal) {
      console.log('serviceId: ', newVal)
      this.listHosts(newVal)
    }
  },
  methods: {
    fetchCase () {
      console.log('#fetchCase:', this.id)
      caseSvc.findOneWithArray(this.id)
        .then(cas => this.cas = cas)
        .catch(err => {
          console.error(err)
          toastr.error('테스트 케이스 조회 오류')
        })
    },
    fetchCall () {
      console.log('#fetchCall:', this.callId)
      callSvc.findOneWithArray(this.callId)
        .then(call => {
          console.log('#call:', call)
          this.cas.description = 'Call # ' + this.callId + ' 로 부터 만들어진 테스트 케이스'
          this.cas.request = call.req
          return call.caseId
        })
        .then(caseSvc.detail)
        .then(cas => this.cas.serviceId = cas.serviceId)
        .catch(err => toastr.error('호출 이력 조회 오류'))
    },
    listServices () {
      console.log('#listServices:')
      return serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
    },
    listHosts (serviceId) {
      console.log('#listHosts:', serviceId)
      return hostSvc.list(serviceId)
        .then(hosts => this.hosts = hosts)
        .catch(err => toastr.error('호스트 목록 조회 실패'))
    },
    saveCase () {
      console.log('#saveCase:', this.cas)
      this.inProgress = true
      caseSvc.save(this.cas)
        .then(id => {
          toastr.success('테스트 케이스가 저장되었습니다.')
          window.location.href = '/cases/' + id
        })
        .catch(err => toastr.error('테스트 케이스 저장 실패'))
        .then(_ => this.inProgress = false)
    },
    initCase () {
      return {
        serviceId: '',
        hostId: '',
        title: '',
        description: '',
        request: {
          method: 'GET',
          path: '/posts/1',
          queries: [],
          headers: [
            {key: 'Accept', value: 'application/json;charset=UTF-8'},
            {key: 'Content-type', value: 'application/json;charset=UTF-8'}
          ],
          body: ''
        },
        response: {
          statusCode: 200,
          headers: [],
          body: ''
        }
      }
    }
  }
}
</script>
