<template>
  <div class="well">
    <form @submit.prevent="add" @reset="hideForm">
      <div class="form-group">
        <label for="title">이름</label>
        <input id="title" type="text" class="form-control" required v-model="suite.title"/>
      </div>
      <div class="form-group">
        <label for="hostA">호스트 A</label>
        <select id="hostA" class="form-control" required v-model="suite.hostA">
          <option :value="host.baseUrl" v-for="host in hosts">[{{host.title}}] {{host.baseUrl}}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="hostB">호스트 B</label>
        <select id="hostB" class="form-control" required v-model="suite.hostB">
          <option :value="host.baseUrl" v-for="host in hosts">[{{host.title}}] {{host.baseUrl}}</option>
        </select>
      </div>
      <div class="form-group text-right">
        <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"/> 추가</button>
        <button type="reset" class="btn btn-sm btn-warning"><i class="fa fa-undo"/> 취소</button>
      </div>
    </form>
  </div>
</template>

<script>
import db from '../../services/database'

export default {
  props: ['suite', 'serviceId', 'form'],
  firebase () {
    return {
      hosts: db.ref('services/' + this.serviceId).child('hosts')
    }
  },
  methods: {
    add () {
      console.log('#add')
      this.$emit('add')
      this.hideForm()
    },
    hideForm () {
      this.$emit('hideForm')
    }
  }
}
</script>
