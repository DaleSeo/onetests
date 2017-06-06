<template>
  <div>
    <table class="ui selectable celled table">
      <thead class="center aligned">
        <tr>
          <th>제목</th>
          <th>케이스수</th>
          <th>상태</th>
          <th>생성 일시</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="suite in revSuites" @click="detail(suite.id)">
          <td>{{suite.title}}</td>
          <td>{{Object.keys(suite.requests).length}}</td>
          <td>{{suite.status || '미완료'}}</td>
          <td>{{suite.createdDate | formatDate}}</td>
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
