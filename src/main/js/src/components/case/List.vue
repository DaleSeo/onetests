<template>
  <div class="ui basic segment">
    <div class="ui active dimmer" v-if="loading">
      <div class="ui text loader">Loading</div>
    </div>

    <div class="ui stackable grid">
      <div class="ten wide column">
        <ServiceFilter v-model="service"/>
        <HostFilter v-model="host" :serviceId="service && service.id"/>
      </div>
      <div class="right aligned six wide column">
        <button class="ui labeled icon button" @click="create">
          <i class="plus icon"/>등록
        </button>
        &nbsp;&nbsp;
      </div>
    </div>

    <table id="tableCase" class="ui very compact celled table">
      <thead class="center aligned">
        <tr>
          <th>메소드</th>
          <th>패스</th>
          <th>서비스</th>
          <th>호스트</th>
          <th>제목</th>
          <th>등록일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cas in cases" @click="detail(cas.id)">
          <td><Method :method="cas.request.method" type="ribbon"/></td>
          <td>{{cas.request.path | limitLength}}</td>
          <td>{{cas.service.code}}</td>
          <td>{{cas.request.host}}</td>
          <td>{{cas.title | limitLength}}</td>
          <td>{{cas.createdDate | date}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import dt from 'datatables.net'
import caseSvc from '../../services/caseSvc'

export default {
  data () {
    return {
      service: null,
      host: null,
      cases: [],
      dataTable: null,
      loading: false
    }
  },
  created () {
    this.fetchCases()
  },
  watch: {
    service (val) {
      let code = val ? val.code : ''
      this.dataTable.column(2).search(code).draw()
    },
    host (val) {
      let baseUrl = val ? val.baseUrl : ''
      this.dataTable.column(3).search(baseUrl).draw()
    }
  },
  methods: {
    fetchCases () {
      this.loading = true
      return caseSvc.list(this.serviceId)
        .then(cases => this.cases = cases)
        .then(_ =>
          this.dataTable = $('#tableCase').DataTable({
            order: [[ 5, "desc" ]],
            columnDefs: [
              // { targets: [0], visible: false },
              { targets: [5], searchable: false }
            ]
          })
        )
        .catch(err => {
          console.error(err)
          throw err
        })
        .catch(err => toastr.error('테스트 케이스 목록 조회 실패'))
        .then(_ => this.loading = false)
    },
    detail (id) {
      window.location.href = `/cases/${id}`
    },
    create () {
      window.location.href = '/cases/new'
    }
  }

}
</script>
