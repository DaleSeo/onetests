<template>
  <div class="ui container">
    <h3 class="ui dividing header">
      <i class="cube icon"></i>
      <div class="content">
        Test Case
        <div class="sub header">
          개별 검증 등록
        </div>
      </div>
    </h3>

    <div style="text-align: right">
      <button class="ui mini primary labeled icon button" @click="rerun">
        <i class="play icon"></i>
        실행
      </button>
      <button class="ui mini labeled icon button" @click="list">
        <i class="list icon"></i>
        목록
      </button>
      <button class="ui mini negative labeled icon button" @click="del">
        <i class="trash icon"></i>
        삭제
      </button>
    </div>

    <h4 class="ui header">
      기본 정보
    </h4>
    <div class="ui segment">
      <p>
        <dt>Case ID</dt>
        <dd>{{cas.id}}</dd>
      </p>
      <p>
        <dt>서비스 코드</dt>
        <dd>{{cas.service.code}}</dd>
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

    <h4 class="ui header">
      요청 정보
    </h4>
    <div class="ui segment">
      <RequestLine :method="cas.request.method" :path="cas.request.path"/>
      <p>
        <dt>쿼리 파리미터</dt>
        <ul>
          <div v-for="(val, key) in cas.request.queries">
            <li>{{key}}: {{val}}</li>
          </div>
        </ul>
      </p>
      <p>
        <dt>요청 헤더</dt>
        <ul>
          <div v-for="(val, key) in cas.request.headers">
            <li>{{key}}: {{val}}</li>
          </div>
        </ul>
      </p>
      <p>
        <dt>바디</dt>
        <dd>
          <pre class="body">{{cas.request.body}}</pre>
        </dd>
      </p>
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
        response: {},
        service: {}
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
        .then(toastr.success('테스트 케이스가 삭제되었습니다.'))
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
