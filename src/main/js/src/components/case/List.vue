<template>
  <div class="ui basic segment">
    <div class="ui active dimmer" v-if="loading">
      <div class="ui text loader">Loading</div>
    </div>

    <div class="ui stackable grid">
      <div class="eight wide column">
        <select class="ui dropdown" v-model="serviceId">
          <option value="">서비스를 선택하세요</option>
          <option :value="service.id" v-for="service in services">{{service.name}}</option>
        </select>
      </div>
      <div class="right aligned eight wide column">
        <button class="ui labeled icon button" @click="create">
          <i class="plus icon"/>등록
        </button>
        &nbsp;&nbsp;
      </div>
    </div>

    <table id="tableCase" class="ui celled table">
      <thead class="center aligned">
        <tr>
          <th>서비스</th>
          <th>메소드</th>
          <th>패스</th>
          <th>제목</th>
          <th>쿼리 개수</th>
          <th>헤더 개수</th>
          <th>등록 일시</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cas in cases" @click="detail(cas.id)">
          <td>{{cas.serviceId}}</td>
          <td><Method :method="cas.request.method" type="ribbon"/></td>
          <td>{{cas.request && cas.request.path.slice(0, 30)}}</td>
          <td>{{cas.title}}</td>
          <td>{{cas.request && keysLength(cas.request.queries)}}</td>
          <td>{{cas.request && keysLength(cas.request.headers)}}</td>
          <td>{{cas.createdDate | formatDate}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import dt from 'datatables.net'
import serviceSvc from '../../services/serviceSvc'
import caseSvc from '../../services/caseSvc'

export default {
  data () {
    return {
      services: [],
      serviceId: '',
      cases: [],
      dataTable: null,
      loading: false
    }
  },
  created () {
    this.fetchServices()
    this.fetchCases()
  },
  watch: {
    serviceId (newVal) {
      this.dataTable.column(0).search(newVal).draw()
    }
  },
  methods: {
    fetchServices () {
      console.log('#fetchServices')
      serviceSvc.list()
        .then(services => this.services = services)
        .catch(err => toastr.error('서비스 목록 조회 실패'))
    },
    fetchCases () {
      this.loading = true
      return caseSvc.list(this.serviceId)
        .then(cases => this.cases = cases)
        .then(_ =>
          this.dataTable = $('#tableCase').DataTable({
            order: [[ 6, "desc" ]],
            columnDefs: [
              { targets: 0, visible: false },
              { targets: [4, 5, 6], searchable: false }
            ]
          })
        )
        .catch(err => toastr.error('테스트 케이스 목록 조회 실패'))
        .then(_ => this.loading = false)
    },
    detail (id) {
      window.location.href = `/cases/${id}`
    },
    keysLength (object) {
      return object ? Object.keys(object).length : 0
    },
    create () {
      window.location.href = '/cases/new'
    }
  }

}
</script>
