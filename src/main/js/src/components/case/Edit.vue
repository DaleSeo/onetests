<template>
  <div class="ui container">
    <Title icon="cube" title="Test Case" subTitle="개별 검증 등록"/>
    <form class="ui form segment" :class="{loading}" @submit.prevent>
      <div class="fields">
        <div class="three wide required field">
          <label>서비스</label>
          <select class="ui fluid search dropdown" v-model="cas.serviceId" required>
            <option :value="service.id" v-for="service in services">{{service.name}}</option>
          </select>
        </div>
        <div class="three wide required field">
          <label>메소드</label>
          <select class="ui fluid search dropdown" v-model="cas.method" required>
            <option v-for="method in methods">{{method}}</option>
          </select>
        </div>
        <div class="ten wide required field">
          <label>패스 (호스트를 제외한 URL)</label>
          <input type="text" v-model="cas.path">
        </div>
      </div>

      <div class="field">
        <label>쿼리 파라미터</label>
        <Entries :entries="cas.queries"/>
      </div>

      <div class="field">
        <label>헤더</label>
        <Entries :entries="cas.headers"/>
      </div>

      <div class="field">
        <label>바디</label>
        <textarea v-model="cas.body"/>
      </div>

      <div class="field">
        <label>설명</label>
        <textarea v-model="cas.description"/>
      </div>

      <button type="submit" class="ui primary button" tabindex="0">저장</button>
      <button type="reset" class="ui secondary button" tabindex="0">취소</button>
    </form>
  </div>
</template>

<script>
import serviceSvc from '../../services/serviceSvc'

import Entries from '../common/Entries.vue'

export default {
  components: { Entries },
  data () {
    return {
      cas: this.initCase(),
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD'],
      services: [],
      loading: false
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
    initCase () {
      return {
        serviceId: '',
        method: 'GET',
        queries: [],
        headers: [
          {key: 'Accept', value: 'application/json;charset=UTF-8'},
          {key: 'Content-type', value: 'application/json;charset=UTF-8'}
        ],
        path: '/posts/1',
        body: '',
        description: ''
      }
    }
  }
}
</script>
