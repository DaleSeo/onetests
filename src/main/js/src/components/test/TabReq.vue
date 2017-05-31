<template>
  <div class="row">
    <div class="col-md-6">
      <div class="panel panel-success">
        <div class="panel-heading">
          <strong>요청 라인 A</strong>
        </div>
        <div class="panel-body">
          <span class="label" :class="className(reqA.method)">{{reqA.method}}</span>
          &nbsp;<strong><em>{{reqA.url.slice(0, 100)}}</em></strong>
        </div>
      </div>
      <div class="panel panel-success">
        <div class="panel-heading">
          <strong>요청 바디 A</strong>
        </div>
        <pre class="body">{{reqA.body}}</pre>
      </div>
    </div>
    <div class="col-md-6">
      <div class="panel panel-danger">
        <div class="panel-heading">
          <strong>요청 라인 B</strong>
        </div>
        <div class="panel-body">
          <span class="label" :class="className(reqA.method)">{{reqA.method}}</span>
          &nbsp;<strong><em>{{reqA.url.slice(0, 100)}}</em></strong>
        </div>
      </div>
      <div class="panel panel-danger">
        <div class="panel-heading">
          <strong>요청 바디 B</strong>
        </div>
        <pre class="body">{{reqA.body}}</pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['reqA', 'reqB'],
  methods: {
    className (method) {
      switch (method) {
        case 'GET': return 'label-success'
        case 'POST': return 'label-info'
        case 'PUT': return 'label-warning'
        case 'DELETE': return 'label-danger'
        default: return 'label-primary'
      }
    }
  }
}
</script>

<style scoped>
.body {
  background-color: white;
  margin-bottom: 0px;
}
</style>
