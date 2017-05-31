<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <strong>호스트 목록</strong> <small>({{hosts.length}})</small>
    </div>
    <ul class="list-group">
      <button class="list-group-item" v-for="host in hosts">
        <b>[{{host.title}}]</b> {{host.baseUrl}}
        <button class="close" @click="del(host.id)">&times;</button>
      </button>
    </ul>
    <div class="panel-body">
      <form @submit.prevent="add(newHost)">
        <div class="row">
          <div class="col-md-3">
            <input type="text" class="form-control" id="title" v-model="newHost.title" placeholder="이름">
          </div>
          <div class="col-md-7">
            <input type="text" class="form-control" id="baseUrl" v-model="newHost.baseUrl" placeholder="URL (http:// 또는 https:// 로 시작할 것)">
          </div>
          <div class="col-md-2">
            <button type="submit" class="btn btn-default">추가</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import hostSvc from '../../services/hostSvc'

export default {
  props: ['id'],
  data () {
    return {
      hosts: [],
      newHost: {
        title: '',
        baseUrl: '',
        serviceId: this.id
      }
    }
  },
  created() {
    this.list()
  },
  methods: {
    list () {
      hostSvc.list(this.id)
        .then(hosts => this.hosts = hosts)
    },
    add (host) {
      console.log('host:', host)
      hostSvc.create(host)
        .then(_ => {
          toastr.success('Created')
          this.newHost.title = ''
          this.newHost.baseUrl = ''
          this.list()
        })
    },
    del (id) {
      hostSvc.remove(id)
        .then(_ => {
          toastr.success('Deleted')
          this.list()
        })
    }
  }
}
</script>
