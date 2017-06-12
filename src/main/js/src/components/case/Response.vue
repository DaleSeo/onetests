<template>
  <div class="ui basic segment form" :class="{loading: inProgress}">
    <div style="text-align: right">
      <button class="ui icon labeled tiny button" :class="color" @click="edit">
        <i class="edit icon"/>{{label}}
      </button>
      <button class="ui icon labeled tiny teal button" @click="record">
        <i class="record icon"/>레코딩
      </button>
    </div>
    <div class="ui segment" v-if="cas.response">
      <div class="field">
        <label>상태 코드</label>
        <input type="text" :disabled="!editing" v-model="cas.response.statusCode">
      </div>

      <div class="field">
        <label>헤더</label>
        <Entries :entries="cas.response.headers"/>
      </div>

      <div class="field">
        <label>바디</label>
        <textarea :disabled="!editing" v-model="cas.response.body"/>
      </div>
    </div>
    <div class="ui info message" v-else>
      등록된 기대 응답 정보가 없습니다.
    </div>
    <pre>{{cas.response}}</pre>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'

import Entries from '../common/Entries.vue'

export default {
  components: {Entries},
  props: ['cas'],
  data () {
    return {
      inProgress: false,
      editing: false
    }
  },
  computed: {
    label() {
      return this.editing ? '저장' : '수정'
    },
    color() {
      return this.editing ? 'yellow' : ''
    }
  },
  methods: {
    edit () {
      if (!this.editing) {
        return this.editing = true
      }

      this.inProgress = true
      caseSvc.saveResponse(this.cas)
        .then(_ => toastr.success('기대 응답 데이터가 저장되었습니다.'))
        .catch(err => toastr.error('기대 응답 데이터가 저장 실패'))
        .then(_ => this.inProgress = false)
        .then(_ => this.editing = false)
    },
    record () {
      this.inProgress = true
      caseSvc.recordResponse(this.cas.id)
        .then(response => this.cas.response = response)
        .then(_ => toastr.success('기대 응답 데이터가 레코딩되었습니다.'))
        .catch(err => toastr.error('기대 응답 데이터 레코딩 실패'))
        .then(_ => this.inProgress = false)
    }
  }
}
</script>
