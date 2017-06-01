<template>
  <div class="ui container">
    <Title icon="cube" title="Test Case" subTitle="개별 검증 상세"/>
    <form class="ui form segment" :class="{loading}" @submit.prevent="saveCase">
      <div class="fields">
        <div class="four wide required field">
          <label>서비스</label>
          <select class="ui fluid search dropdown" v-model="cas.serviceId" required>
            <option :value="service.id" v-for="service in services">{{service.name}}</option>
          </select>
        </div>
        <div class="twelve wide required field">
          <label>제목</label>
          <input type="text" v-model="cas.title" readonly disabled>
        </div>
      </div>

      <div class="fields">
        <div class="four wide required field">
          <label>메소드</label>
          <select class="ui fluid search dropdown" v-model="cas.request.method" required>
            <option v-for="method in methods">{{method}}</option>
          </select>
        </div>
        <div class="twelve wide required field">
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
    <pre>{{cas}}</pre>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import serviceSvc from '../../services/serviceSvc'

import utils from '../../services/utils'

export default {
  props: ["id"],
  data() {
    return {
      cas: {
        request: {},
        response: {}
      },
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD'],
      services: [],
      loading: false,
      inProgress: false
    }
  },
  created () {
    this.listServices()
      .then(_ => this.detail())
      .then( _ => this.loading = false)
  },
  methods: {
    listServices () {
      this.loading = true
      console.log('ApiList.vue#listServices()')
      return serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
    },
    detail () {
      return caseSvc.detail(this.id)
        .then(cas => this.cas = cas)
    },
    list () {
      window.location.href = '/cases'
    },
    rerun () {
      window.location.href = '/console'
    },
    del () {
      caseSvc.remove(this.id)
        .then(this.list)
    },
    methodClass (method) {
      return utils.methodClass(method)
    }
  }
}
</script>

<style scoped>
.body {
  background-color: white;
  margin-bottom: 0px;
}

.dl-horizontal {
  margin-bottom: 0px;
}
</style>
