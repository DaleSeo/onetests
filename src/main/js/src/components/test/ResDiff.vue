<template>
  <div class="modal fade" :class="{in: show}" :style="style">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" @click="close">&times;</button>
          <h4 class="modal-title">상세 결과</h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-sm-12">
              <div class="alert alert-info">
                <span v-if="result">{{result}}</span>
                <span v-else>N/A</span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6">
              <b>상태</b>
              <pre class="bg-success">{{resA.statusCode}} {{resA.statusText}}</pre>
              <b>헤더</b>
              <pre class="bg-success">{{resA.headers}}</pre>
              <b>바디</b>
              <pre class="bg-success">{{resA.body}}</pre>
            </div>
            <div class="col-sm-6">
              <b>상태</b>
              <pre class="bg-danger">{{resB.statusCode}} {{resB.statusText}}</pre>
              <b>헤더</b>
              <pre class="bg-danger">{{resB.headers}}</pre>
              <b>바디</b>
              <pre class="bg-danger">{{resB.body}}</pre>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['result', 'resA', 'resB', 'show'],
  computed: {
    style() {
      return this.show ? {display: 'block'} : {}
    }
  },
  methods: {
    close () {
      this.$emit('close')
    }
  }
}
</script>
