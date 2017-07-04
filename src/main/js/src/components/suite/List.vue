<template>
  <div>
    <table class="ui selectable celled table">
      <thead class="center aligned">
        <tr>
          <th>제목</th>
          <th>케이스수</th>
          <th>서비스</th>
          <!-- <th>호스트</th> -->
          <th>등록자</th>
          <th>등록 일시</th>
        </tr>
      </thead>
      <tbody>
        <Item :key="suite.id" :suite="suite" v-for="suite in revSuites"/>
      </tbody>
    </table>
  </div>
</template>

<script>
import suiteSvc from '../../services/suiteSvc'
import Item from './Item.vue'

export default {
  components: {Item},
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
