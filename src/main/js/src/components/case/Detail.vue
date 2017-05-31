<template>
  <div class="ui container">
    <h3 class="ui dividing header">
      <i class="cube icon"></i>
      <div class="content">
        Test Case
        <div class="sub header">
          개별 검증 상세
        </div>
      </div>
    </h3>

    <div class="ui right aligned basic segment">
      <button class="ui mini labeled icon button" @click="list">
        <i class="list icon"></i>
        목록
      </button>
      <button class="ui mini positive labeled icon button" @click="rerun">
        <i class="play icon"></i>
        재실행
      </button>
      <button class="ui mini negative labeled icon button" @click="del">
        <i class="trash icon"></i>
        삭제
      </button>
    </div>

    <div class="ui segment">
      <h4 class="ui dividing  header">
        기본 정보
      </h4>
      <p>
        <dt>Case ID</dt>
        <dd>{{cas.id}}</dd>
      </p>
      <p>
        <dt>Service ID</dt>
        <dd>{{cas.serviceId}}</dd>
      </p>
      <p>
        <dt>수행 일시</dt>
        <dd>{{cas.createdDate | formatDate}}</dd>
      </p>
      <p>
        <dt>작업자</dt>
        <dd>{{cas.createdBy || '아무게'}}</dd>
      </p>
    </div>

    <div class="ui segment">
      <h4 class="ui dividing  header">
        요청 정보
      </h4>
      <p>
        <dt>요청 라인</dt>
        <dd>
          <span class="label" :class="methodClass(cas.request.method)">{{cas.request.method}}</span>
          &nbsp;<em>{{cas.request.url}}</em>
        </dd>
      </p>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>요청 쿼리</strong>
      </div>
      <div class="panel-body" >
        <dl class="dl-horizontal">
          <div v-for="(val, key) in cas.request.queries">
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
          <div v-for="(val, key) in cas.request.headers">
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
      <pre class="body">{{cas.request.body}}</pre>
    </div>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import utils from '../../services/utils'

export default {
  props: ["id"],
  data() {
    return {
      cas: {
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
      caseSvc.detail(this.id)
        .then(cas => this.cas = cas)
    },
    list () {
      window.location.href = '/cases'
    },
    rerun () {
      window.location.href = '/console'
    },
    del () {
      caseSvc.remove(this.id)
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
