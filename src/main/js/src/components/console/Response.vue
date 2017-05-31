<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <i class="fa fa-arrow-circle-left"/> <strong>응답</strong>
    </div>
    <div class="panel-body">
      <ProgressBar v-if="inProgress"/>

      <div class="alert alert-danger" v-if="response.error">
        <strong>호출 실패</strong> {{response.error}}
      </div>

      <div class="alert alert-success" v-if="response.statusCode">
        <strong>{{response.statusCode}}</strong> {{response.statusMessage}}
      </div>

      <div v-if="response.body">
        <ul class="nav nav-tabs">
          <li :class="{active: tab === 'headers'}" @click="tab = 'headers'"><a href="#headers">헤더</a></li>
          <li :class="{active: tab === 'body'}" @click="tab = 'body'"><a href="#body">바디</a></li>
        </ul>

        <div id="headers" v-if="tab === 'headers'" class="well">
          <dl class="dl-horizontal">
            <div v-for="(val, key) in response.headers">
              <dt>{{key}}</dt>
              <dd>{{val}}</dd>
            </div>
          </dl>
        </div>

        <div id="body" v-if="tab === 'body'">
          <pre>{{response.body}}</pre>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProgressBar from '../common/ProgressBar.vue'

export default {
  components: {ProgressBar},
  props: ['response', 'inProgress'],
  data () {
    return {
      tab: 'body',
    }
  }
}
</script>

<style scoped>
.dl-horizontal {
  margin-bottom: 0px;
}
</style>
