<template>
  <form @submit.prevent @keyup.enter="callApi">
    <div class="panel panel-default">
      <div class="panel-heading">
        <i class="fa fa-arrow-circle-right"/> <strong>요청</strong>
      </div>
      <div class="panel-body">
        <div class="row">
          <div class="col-sm-3">
            <select class="form-control" v-model="request.method" required>
              <option v-for="method in methods">{{method}}</option>
            </select>
          </div>
          <div class="col-sm-9">
            <input type="text" class="form-control" v-model="request.url" required/>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <ul class="nav nav-tabs">
              <li :class="{active: tab === 'queries'}" @click="tab = 'queries'"><a>쿼리 파라미터</a></li>
              <li :class="{active: tab === 'headers'}" @click="tab = 'headers'"><a>헤더</a></li>
              <li :class="{active: tab === 'body'}" @click="tab = 'body'"><a>바디</a></li>
            </ul>

            <div id="queries" v-if="tab === 'queries'">
              <Entries :entries="request.queries"/>
            </div>

            <div id="headers" v-if="tab === 'headers'">
              <Entries :entries="request.headers"/>
            </div>

            <div id="body" v-if="tab === 'body'">
              <textarea class="form-control" rows="5" v-model="request.body"/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import Entries from '../common/Entries.vue'

export default {
  components: {Entries},
  props: ['request'],
  data () {
    return {
      tab: 'body',
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD']
    }
  },
  methods: {
    callApi () {
      this.$emit('callApi')
    }
  }
}
</script>

<style scoped>
.row {
  padding-top: 10px;
  padding-bottom: 10px;
}
.nav {
  margin-bottom: 15px;
}
</style>
