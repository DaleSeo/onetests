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
        <div class="six wide required field">
          <label>서비스</label>
          <ServiceDropdown v-model="cas.serviceId"/>
        </div>
        <div class="ten wide required field">
          <label>호스트</label>
          <HostDropdown v-model="cas.hostId" :serviceId="cas.serviceId"/>
        </div>
      </div>

      <button type="submit" class="ui primary button" :class="{loading: inProgress}" tabindex="0">저장</button>
      <button type="reset" class="ui secondary button" tabindex="0">취소</button>

      <pre>{{cas}}</pre>
    </form>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import callSvc from '../../services/callSvc'

import Entries from '../common/Entries.vue'

export default {
  components: { Entries },
  props: ['id'],
  data () {
    return {
      cas: this.initCase(),
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD'],
      callId: this.$route.query.callId || '',
      loading: false,
      inProgress: false
    }
  },
  created () {
    if (this.id) {
      this.fetchCase ()
    }
    if (this.callId) {
      this.fetchCall ()
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
      callSvc.detail(this.callId)
        .then(call => {
          console.log('#call:', call)
          this.cas.description = 'Call #' + this.callId + ' 로 부터 만들어진 테스트 케이스'
          this.cas.request = call.request
        })
        .catch(err => {
          console.error(err)
          return err
        })
        .catch(err => toastr.error('호출 이력 조회 오류'))
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
          host: '',
          path: '',
          queries: {},
          headers: {},
          body: ''
        },
        response: {
          statusCode: 200,
          headers: {},
          body: ''
        }
      }
    }
  }
}
</script>
