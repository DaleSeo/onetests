<template>
  <div>
    <table class="table table-hover table-bordered">
      <thead>
        <tr>
          <th>ID</th>
          <th>검증 제목</th>
          <th>생성 일시</th>
          <th>작업자</th>
          <th>케이스 개수</th>
          <th>상태</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="suite in revSuites" @click="detail(suite.id)">
          <td>{{suite.id}}</td>
          <td>{{suite.title}}</td>
          <td>{{suite.createdDate | formatDate}}</td>
          <td>{{suite.createdBy || '아무게'}}</td>
          <td>{{Object.keys(suite.requests).length}}</td>
          <td>{{suite.status || '미완료'}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import suiteSvc from '../../services/suiteSvc'

export default {
  data () {
    return {
      suites: []
    }
  },
  computed: {
    revSuites() {
      return this.suites.slice().reverse();
    }
  },
  created () {
    this.list()
  },
  methods: {
    list () {
      suiteSvc.list()
        .then(suites => this.suites = suites)
    },
    detail (id) {
      window.location.href = `/suites/${id}`
    }
  }
}
</script>

<style scoped>
.table > thead > tr > th, td {
  text-align: center;
  vertical-align: middle;
}

.table > thead > tr > th {
  background-color: #f5f5f5;
}
</style>
