<template>
  <div class="container">
    <h3>
      <i class="fa fa-cube"/> <b>Test Case</b> <small>검증 상세</small>
      <div class="pull-right">
        <button class="btn btn-sm btn-default" title="목록" @click="list">
          <i class="fa fa-list"/> 검증 목록
        </button>
        <button class="btn btn-sm btn-primary" title="목록" @click="rerun">
          <i class="fa fa-play"/> 다시 실행
        </button>
        <button class="btn btn-sm btn-danger" title="삭제" @click="del">
          <i class="fa fa-trash"/> 삭제
        </button>
      </div>
    </h3>
    <hr/>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>기본 정보</strong>
      </div>
      <div class="panel-body" >
        <dl class="dl-horizontal">
            <dt>ID</dt>
            <dd>{{call.id}}</dd>
            <dt>수행 일시</dt>
            <dd>{{call.createdDate | formatDate}}</dd>
            <dt>작업자</dt>
            <dd>{{call.createdBy || '아무게'}}</dd>
        </dl>
      </div>
    </div>

    <hr/>
    <p class="lead"><strong>요청 상세</strong><p>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>요청 라인</strong>
      </div>
      <div class="panel-body">
        <span class="label" :class="methodClass(call.request.method)">{{call.request.method}}</span>
        &nbsp;<strong><em>{{call.request.url}}</em></strong>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>요청 쿼리</strong>
      </div>
      <div class="panel-body" >
        <dl class="dl-horizontal">
          <div v-for="(val, key) in call.request.queries">
            <dt>{{key}}</dt>
            <dd>{{val}}</dd>
          </div>
        </dl>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>요청 헤더</strong>
      </div>
      <div class="panel-body" >
        <dl class="dl-horizontal">
          <div v-for="(val, key) in call.request.headers">
            <dt>{{key}}</dt>
            <dd>{{val}}</dd>
          </div>
        </dl>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>요청 바디</strong>
      </div>
      <pre class="body">{{call.request.body}}</pre>
    </div>

    <hr/>
    <p class="lead"><strong>응답 상세</strong><p>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>응답 상태</strong>
      </div>
      <div class="panel-body">
        <strong>{{call.response.statusCode}} {{call.response.statusMessage}}</strong>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>응답 헤더</strong>
      </div>
      <div class="panel-body" >
        <dl class="dl-horizontal">
          <div v-for="(val, key) in call.response.headers">
            <dt>{{key}}</dt>
            <dd>{{val}}</dd>
          </div>
        </dl>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>응답 바디</strong>
      </div>
      <pre class="body">{{call.response.body}}</pre>
    </div>
  </div>
</template>

<script>
import callSvc from '../../services/callSvc'
import utils from '../../services/utils'

export default {
  props: ["id"],
  data() {
    return {
      call: {
        request: {},
        response: {}
      }
    }
  },
  created () {
    this.detail()
  },
  methods: {
    detail () {
      callSvc.detail(this.id)
        .then(call => this.call = call)
    },
    list () {
      window.location.href = '/cases'
    },
    rerun () {
      window.location.href = '/console'
    },
    del () {
      callSvc.remove(this.id)
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
