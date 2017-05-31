<template>
  <div>
    <table class="ui selectable celled table">
      <thead class="center aligned">
        <tr>
          <th>서비스</th>
          <th>메소드</th>
          <th>URL</th>
          <th>쿼리 개수</th>
          <th>헤더 개수</th>
          <th>생성자</th>
          <th>생성 시각</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cas in revCases" @click="detail(cas.id)">
          <td>{{cas.serviceId}}</td>
          <td>{{cas.request.method}}</td>
          <td>{{cas.request.url}}</td>
          <td>{{keysLength(cas.request.queries)}}</td>
          <td>{{keysLength(cas.request.headers)}}</td>
          <td>{{cas.createdBy || '아무게'}}</td>
          <td>{{cas.createdDate | formatDate}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: ["cases"],
  computed: {
    revCases() {
      return this.cases.slice().reverse();
    }
  },
  methods: {
    detail (id) {
      window.location.href = `/cases/${id}`
    },
    keysLength (object) {
      return object ? Object.keys(object).length : 0
    }
  }
}
</script>
