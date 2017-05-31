<template>
  <div class="container">
    <h3>
      <i class="fa fa-cubes"/> <b>Test Suite</b> <small>일괄 검증 상세</small>

      <div class="pull-right">
        <button class="btn btn-sm btn-default" title="목록" @click="list">
          <i class="fa fa-list"/> 목록
        </button>
        <button class="btn btn-sm btn-primary" title="목록" @click="run">
          <i class="fa fa-play"/> 실행
        </button>
        <button class="btn btn-sm btn-danger" title="삭제" @click="del">
          <i class="fa fa-trash"/> 삭제
        </button>
      </div>
    </h3>
    <hr/>

    <div class="row">
      <div class="col-md-6">
        <div class="panel panel-default">
          <div class="panel-heading">
            <strong>기본 정보</strong>
          </div>
          <div class="panel-body" >
            <dl class="dl-horizontal">
                <dt>ID</dt>
                <dd>{{suite.id}}</dd>
                <dt>검증 제목</dt>
                <dd>{{suite.title}}</dd>
                <dt>검증 설명</dt>
                <dd>{{suite.description}}</dd>
                <dt>생성 일시</dt>
                <dd>{{suite.createdDate | formatDate}}</dd>
                <dt>작업자</dt>
                <dd>{{suite.createdBy || '아무게'}}</dd>
            </dl>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="panel panel-default">
          <div class="panel-heading">
            <strong>검증 결과</strong>
          </div>
          <div class="panel-body" v-if="suite.result">
            <dl class="dl-horizontal">
                <dt>성공</dt>
                <dd>{{suite.result.success}}</dd>
                <dt>실패</dt>
                <dd>{{suite.result.failure}}</dd>
                <dt>합계</dt>
                <dd>{{suite.result.total}}</dd>
            </dl>
            <a class="btn btn-info" :href="caseListHref">테스트 케이스 목록 확인</a>
          </div>
          <div class="panel-body" v-else>
            실행 버튼을 눌러 실행하세요
          </div>
        </div>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>테스트 케이스 목록</strong>
      </div>
      <ul class="list-group">
        <Case :key="index" :request="request" v-for="(request, index) in suite.requests"/>
      </ul>
    </div>
  </div>
</template>

<script>
import suiteSvc from '../../services/suiteSvc'

import Case from './Case.vue'

export default {
  components: {Case},
  props: ["id"],
  created() {
    this.detail()
  },
  computed: {
    caseListHref () {
      return '/cases?suiteId=' + this.suite.id
    }
  },
  data () {
    return {
      suite: {}
    }
  },
  methods: {
    detail () {
      suiteSvc.detail(this.id)
        .then(suite => this.suite = suite)
        .catch(err => toastr.error('스위트 상세 조회 실패'))
    },
    list () {
      window.location.href = '/suites'
    },
    run () {
      suiteSvc.run(this.id)
        .then(res => toastr.success('스위트 실행 성공'))
        .then(this.detail)
        .catch(err => toastr.error('스위트 실행 실패'))
    },
    del () {
      suiteSvc.remove(this.id)
        .then(suite => this.suite = suite)
        .then(_ => {
          toastr.error('테스트 스위트가 삭제되었습니다')
          this.list()
        })
        .catch(err => toastr.error('테스트 스위트 삭제 실패'))
    }
  }
}
</script>

<style scoped>
.dl-horizontal {
  margin-bottom: 0px;
}
</style>
