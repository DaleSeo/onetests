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

    <div class="ui grid">
      <div class="right aligned column">
        <button class="ui mini primary labeled icon button" @click="run">
          <i class="play icon"></i>
          실행
        </button>
        <button class="ui mini labeled icon button" @click="list">
          <i class="list icon"></i>
          목록
        </button>
        <button class="ui mini yellow labeled icon button" @click="edit">
          <i class="trash icon"></i>
          수정
        </button>
        <button class="ui mini negative labeled icon button" @click="del">
          <i class="trash icon"></i>
          삭제
        </button>
      </div>
    </div>

    <div class="ui grid">
      <div class="three wide column">
        <div class="ui vertical fluid tabular menu">
          <a class="item" data-tab="basics">기본 정보</a>
          <a class="item" data-tab="request">요청 상세</a>
          <a class="item" data-tab="response">기대 응답</a>
          <a class="active item" data-tab="history">호출 이력</a>
        </div>
      </div>
      <div class="thirteen wide stretched column">
        <div class="ui tab" data-tab="basics">
          <Basics :cas="cas"/>
        </div>
        <div class="ui tab" data-tab="request">
          <Request :cas="cas"/>
        </div>
        <div class="ui tab" data-tab="response">
          <Response :cas="cas"/>
        </div>
        <div class="ui active tab" data-tab="history">
          <History :caseId="id"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import testRunner from '../../services/testRunner'
import utils from '../../services/utils'

import Basics from './Basics.vue'
import Request from './Request.vue'
import Response from './Response.vue'
import History from './History.vue'

export default {
  components: {Basics, Request, Response, History},
  props: ['id'],
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
  mounted () {
    $('.menu .item').tab()
  },
  methods: {
    detail () {
      caseSvc.detail(this.id)
        .then(cas => this.cas = cas)
    },
    list () {
      window.location.href = '/cases'
    },
    run () {
      testRunner.runSingle(this.id)
        .then(call => toastr.success('테스트 실행 완료'))
        .catch(err => toastr.error('테스트 실행 실패'))
    },
    del () {
      caseSvc.remove(this.id)
        .then(toastr.success('테스트 케이스가 삭제되었습니다.'))
        .then(this.list)
    },
    edit () {
      window.location.href = '/cases/' + this.id + '/edit'
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
