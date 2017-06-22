<template>
  <div>
    <h5 class="ui top attached inverted small header">
      <i class="arrow circle left icon"/>
      <div class="content">
        응답
      </div>
    </h5>
    <div class="ui attached segment" :class="{loading: inProgress}">
      <div class="ui negative message" v-if="response.error">
        <div class="header">
          호출 실패
        </div>
        <p>{{response.error}}</p>
      </div>

      <div class="ui positive message" v-if="response.statusCode">
        <div class="header">
          {{response.statusCode}}
        </div>
        <p>{{response.statusMessage}}</p>
      </div>

      <div id="resTab" v-show="response.body">
        <div class="ui tabular menu">
          <a class="item" data-tab="res-first">헤더</a>
          <a class="active item" data-tab="res-second">바디</a>
        </div>
        <div class="ui tab" data-tab="res-first">
          <table class="ui definition table">
            <tbody>
              <tr v-for="(val, key) in response.headers">
                <td class="two wide column">{{key}}</td>
                <td>{{val}}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="ui active tab" data-tab="res-second">
          <pre>{{response.body}}</pre>
        </div>
      </div>

      <div style="text-align: right" v-if="callId">
        <button type="button" class="ui labeled icon small button" @click="detail">
          <i class="browser icon"/> 상세
        </button>
        <button type="button" class="ui labeled icon yellow small button" @click="share">
          <i class="share alternate icon"/> 공유
        </button>
        <button type="button" class="ui labeled icon teal small button" @click="register">
          <i class="cube icon"/>등록
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import ProgressBar from '../common/ProgressBar.vue'

export default {
  components: {ProgressBar},
  props: ['response', 'inProgress', 'callId'],
  updated () {
    $('#resTab .menu .item').tab({
      context: $('#resTab')
    })
  },
  data () {
    return {
      tab: 'body',
    }
  },
  methods: {
    detail () {
      window.location.href = window.location.origin + '/calls/' + this.callId
    },
    share () {
      $('#modalShare').modal('show')
    },
    register () {
      window.location.href = window.location.origin + '/cases/new?callId=' + this.callId
    }
  }
}
</script>
