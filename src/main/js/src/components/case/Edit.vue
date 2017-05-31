<template>
  <div class="ui container">
    <Title icon="cube" title="Test Case" subTitle="개별 검증 등록"/>
    <form class="ui form segment" :class="{loading}" @submit.prevent="saveCase">
      <div class="fields">
        <div class="three wide required field">
          <label>서비스</label>
          <select class="ui fluid search dropdown" v-model="cas.serviceId" required>
            <option :value="service.id" v-for="service in services">{{service.name}}</option>
          </select>
        </div>
        <div class="three wide required field">
          <label>메소드</label>
          <select class="ui fluid search dropdown" v-model="cas.request.method" required>
            <option v-for="method in methods">{{method}}</option>
          </select>
        </div>
        <div class="ten wide required field">
          <label>패스 (호스트를 제외한 URL)</label>
          <input type="text" v-model="cas.request.path">
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

      <div class="field">
        <label>설명</label>
        <textarea v-model="cas.description"/>
      </div>

      <button type="submit" class="ui primary button" :class="{loading: inProgress}" tabindex="0">저장</button>
      <button type="reset" class="ui secondary button" tabindex="0">취소</button>
    </form>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import serviceSvc from '../../services/serviceSvc'

import Entries from '../common/Entries.vue'

export default {
  components: { Entries },
  data () {
    return {
      cas: this.initCase(),
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD'],
      services: [],
      loading: false,
      inProgress: false
    }
  },
  created () {
    this.listServices()
  },
  methods: {
    listServices () {
      this.loading = true
      console.log('ApiList.vue#listServices()')
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
        .then(_ => this.loading = false)
    },
    saveCase () {
      this.inProgress = true
      caseSvc.save(this.cas)
        .then(_ => toastr.success('테스트 케이스가 저장되었습니다.'))
        .catch(err => toastr.error('테스트 케이스 저장 실패'))
        .then(_ => this.inProgress = false)
    },
    initCase () {
      return {
        serviceId: '',
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
        description: ''
      }
    }
  }
}
</script>
