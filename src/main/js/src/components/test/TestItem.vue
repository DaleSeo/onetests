<template>
  <button class="list-group-item" :class="classStatus(status)" @click="toggle">
    <span class="label" :class="classMethod(test.api.method)">{{test.api.method}}</span>
    &nbsp;<em>{{test.api.path.slice(0, 50)}}</em>
    <span class="pull-right">
      <b>{{status}}</b>
      <span v-if="status == '비교 완료'">
        <button class="btn btn-xs btn-primary" v-if="hasPassed" @click="pick">PASS</button>
        <button class="btn btn-xs btn-danger" v-else @click="pick">FAIL</button>
      </span>
    </span>
  </button>
</template>

<script>
export default {
  props: ['test', 'suiteId'],
  computed: {
    status () {
      if (this.test.result) {
        return '비교 완료'
      }
      if (this.test.errA || this.test.errB) {
        return '오류 발생'
      }
      if (this.test.resA && this.test.resB) {
        return '호출 완료'
      }
      if (this.test.reqA && this.test.reqB) {
        return '적재 완료'
      }
      return '준비'
    },
    hasPassed () {
      return this.test.result
        && this.test.result.statusEqual
        && this.test.result.headersEqual
        && this.test.result.bodyEqual
    }
  },
  data () {
    return {
      active: false,
    }
  },
  methods: {
    classMethod (method) {
      switch (method) {
        case 'GET': return 'label-success'
        case 'POST': return 'label-info'
        case 'PUT': return 'label-warning'
        case 'DELETE': return 'label-danger'
        default: return 'label-primary'
      }
    },
    classStatus (status) {
      switch (status) {
        case '비교 완료': return 'list-group-item-info'
        case '호출 완료': return 'list-group-item-success'
        case '적재 완료': return 'list-group-item-warning'
        case '오류 발생': return 'list-group-item-danger'
        default: return 'list-group-item-primary'
      }
    },
    toggle () {
      this.active = !this.active
    },
    pick () {
      this.$emit('pick', this.test)
    }
  }
}
</script>
