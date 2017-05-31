<template>
  <div class="row">
    <div class="col-sm-6">
      <div class="panel panel-success">
        <div class="panel-heading">
          <strong>응답 상태 A</strong>
        </div>
        <div class="panel-body">
          <strong>{{resA.statusCode}} {{resA.statusText}}</strong>
        </div>
      </div>
      <div class="panel panel-success">
        <div class="panel-heading">
          <strong>응답 헤더 A</strong>
        </div>
        <div class="panel-body" >
          <dl class="dl-horizontal">
            <div v-for="(val, key) in resA.headers">
              <dt>{{key}}</dt>
              <dd>{{val}}</dd>
            </div>
          </dl>
        </div>
      </div>
      <div class="panel panel-success">
        <div class="panel-heading">
          <strong>응답 바디 A</strong>
        </div>
        <pre class="body">{{resA.body}}</pre>
      </div>
    </div>
    <div class="col-sm-6">
      <div class="panel panel-danger">
        <div class="panel-heading">
          <strong>응답 상태 B</strong>
        </div>
        <div class="panel-body">
          <strong>{{resB.statusCode}} {{resB.statusText}}</strong>
        </div>
      </div>
      <div class="panel panel-danger">
        <div class="panel-heading">
          <strong>응답 헤더 B</strong>
        </div>
        <div class="panel-body" >
          <dl class="dl-horizontal">
            <div v-for="(val, key) in resB.headers">
              <dt>{{key}}</dt>
              <dd>{{val}}</dd>
            </div>
          </dl>
        </div>
      </div>
      <div class="panel panel-danger">
        <div class="panel-heading">
          <strong>응답 바디 B</strong>
        </div>
        <pre class="body">{{resB.body}}</pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['resA', 'resB']
}
</script>

<style scoped>
.dl-horizontal {
  margin-bottom: 0px;
}

.body {
  background-color: white;
  margin-bottom: 0px;
}
</style>
