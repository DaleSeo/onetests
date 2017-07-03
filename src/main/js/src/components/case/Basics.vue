<template>
  <div class="ui basic segment form" :class="{loading: inProgress}">
    <div style="text-align: right">
      <button class="ui icon labeled tiny button" @click="list">
        <i class="list icon"/>목록
      </button>
      <button class="ui icon labeled tiny button" :class="color" @click="edit">
        <i class="edit icon"/>{{label}}
      </button>
      <button class="ui icon labeled tiny yellow button" @click="clone">
        <i class="clone icon"/>복제
      </button>
      <!-- <button class="ui icon labeled tiny red button" @click="del">
        <i class="trash icon"/>삭제
      </button> -->
    </div>
    <div class="ui segment" v-if="cas.request">
      <div class="field">
        <label>ID</label>
        <input type="text" readonly :value="cas.id">
      </div>

      <div class="field required">
        <label>제목</label>
        <input type="text" v-model="cas.title" :disabled="readonly">
      </div>

      <div class="field">
        <label>설명</label>
        <textarea v-model="cas.description" :disabled="readonly"/>
      </div>

      <div class="fields">
        <div class="six wide required field">
          <label>서비스</label>
          <ServiceDropdown v-model="cas.serviceId" :readonly="true"/>
        </div>
        <div class="ten wide required field">
          <!-- <label>호스트</label>
          <HostDropdown v-model="cas.hostId" :serviceId="cas.serviceId" :readonly="true"/> -->
          <label>디폴트 환경</label>
          <EnvironmentDropdown v-model="cas.environment" :fluid="true" :readonly="readonly" look="selection"/>
        </div>
      </div>

      <div class="field">
        <label>생성 일시</label>
        <input type="text" readonly :value="cas.createdDate | formatDate">
      </div>

      <div class="field">
        <label>생성자</label>
        <input type="text" readonly :value="cas.createdBy | userName">
      </div>

      <div class="field">
        <label>변경 일시</label>
        <input type="text" readonly :value="cas.lastModifiedDate | formatDate">
      </div>

      <div class="field">
        <label>변경자</label>
        <input type="text" readonly :value="cas.lastModifiedBy | userName">
      </div>
    </div>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import Pairs from '../common/Pairs.vue'
import EnvironmentDropdown from '../common/EnvironmentDropdown.vue'

export default {
  components: {Pairs, EnvironmentDropdown},
  props: ['cas'],
  data () {
    return {
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD'],
      inProgress: false,
      readonly: true
    }
  },
  computed: {
    label() {
      return this.readonly ? '수정' : '저장'
    },
    color() {
      return this.readonly ? '' : 'pink'
    }
  },
  methods: {
    edit () {
      if (this.readonly) return this.readonly = false
      this.inProgress = true
      let aCase = {}
      aCase.id = this.cas.id
      aCase.title = this.cas.title
      aCase.description = this.cas.description
      aCase.environmentId = this.cas.environmentId
      aCase.serviceId = this.cas.serviceId
      caseSvc.saveBasics(aCase)
        .then(_ => toastr.success('기본 정보가 저장되었습니다.'))
        .catch(err => toastr.error('기본 정보 저장 실패'))
        .then(_ => this.inProgress = false)
        .then(_ => this.readonly = true)
    },
    list () {
      window.location.href = '/cases'
    },
    clone () {
      caseSvc.clone(this.cas.id)
        .then(cas => location.href = '/cases/' + cas.id)
        .tehn(_ => toastr('테스크 케이스가 복제되었습다.'))
        .catch(err => toastr.error('테스트 케이스 복제 실패'))
    },
    del () {
      caseSvc.remove(this.cas.id)
        .then(toastr.success('테스트 케이스가 삭제되었습니다.'))
        .then(this.list)
    }
  }
}
</script>

<style scoped>
input[readonly='readonly'] {
  border: none !important;
}
</style>
