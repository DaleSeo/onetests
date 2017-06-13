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
        <input type="text" :disabled="readonly" v-model="cas.response.statusCode">
      </div>

      <div class="field">
        <label>헤더</label>
        <Pairs v-model="cas.response.headers" :readonly="readonly"/>
      </div>

      <div class="field">
        <label>바디</label>
        <textarea :disabled="readonly" v-model="cas.response.body"/>
      </div>
    </div>
    <div class="ui info message" v-else>
      등록된 기대 응답 정보가 없습니다.
    </div>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import Pairs from '../common/Pairs.vue'

export default {
  components: {Pairs},
  props: ['cas'],
  data () {
    return {
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
      if (this.readonly) {
        return this.readonly = false
      }

      this.inProgress = true
      caseSvc.saveResponse(this.cas)
        .then(_ => toastr.success('기대 응답 데이터가 저장되었습니다.'))
        .catch(err => toastr.error('기대 응답 데이터가 저장 실패'))
        .then(_ => this.inProgress = false)
        .then(_ => this.readonly = true)
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
