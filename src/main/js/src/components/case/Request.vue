<template>
  <div class="ui basic segment form" :class="{loading: inProgress}">
    <div style="text-align: right">
      <button class="ui icon labeled tiny button" :class="color" @click="edit">
        <i class="edit icon"/>{{label}}
      </button>
    </div>
    <div class="ui segment" v-if="cas.request">
      <div class="required field">
        <label>호스트</label>
        <input required v-model="cas.request.host" :disabled="readonly"/>
      </div>

      <div class="fields">
        <div class="four wide required field">
          <label>메소드</label>
          <MethodDropdown v-model="cas.request.method" :readonly="readonly"/>
        </div>
        <div class="twelve wide required field">
          <label>패스</label>
          <input type="text" required v-model="cas.request.path" :disabled="readonly"/>
        </div>
      </div>

      <div class="field">
        <label>쿼리</label>
        <Pairs v-model="cas.request.queries" :readonly="readonly"/>
      </div>

      <div class="field">
        <label>헤더</label>
        <Pairs v-model="cas.request.headers" :readonly="readonly"/>
      </div>

      <div class="field">
        <label>바디</label>
        <textarea v-model="cas.request.body" :disabled="readonly"/>
      </div>
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
      if (this.readonly) return this.readonly = false
      this.inProgress = true
      caseSvc.saveRequest(this.cas)
        .then(_ => toastr.success('요청 상세 데이터가 저장되었습니다.'))
        .catch(err => toastr.error('요청 상세 데이터가 저장 실패'))
        .then(_ => this.inProgress = false)
        .then(_ => this.readonly = true)
    }
  }
}
</script>
