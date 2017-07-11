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
      <div class="three wide column">
        <div class="ui vertical fluid tabular menu">
          <a class="active item" data-tab="basics">기본 정보</a>
          <a class="item" data-tab="request">요청 상세</a>
          <a class="item" data-tab="response">기대 응답</a>
          <a class="item" data-tab="run">검증 수행</a>
          <a class="item" data-tab="social">의견 교환</a>
        </div>
      </div>
      <div class="thirteen wide stretched column">
        <div class="ui active tab" data-tab="basics">
          <Basics :cas="cas"/>
        </div>
        <div class="ui tab" data-tab="request">
          <Request :cas="cas"/>
        </div>
        <div class="ui tab" data-tab="response">
          <Response :cas="cas"/>
        </div>
        <div class="ui tab" data-tab="run">
          <Run :caseId="id"/>
        </div>
        <div class="ui tab" data-tab="social">
          <Social :cas="cas"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import utils from '../../services/utils'

import Basics from './Basics.vue'
import Request from './Request.vue'
import Response from './Response.vue'
import Run from './Run.vue'
import Social from './Social.vue'

export default {
  components: {Basics, Request, Response, Run, Social},
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
  watch: {
    'cas.environment': function (val) {
      console.log('#watch cas.environment:', val)
      if (val) this.cas.environmentId = val.id
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
    }
  }
}
</script>
