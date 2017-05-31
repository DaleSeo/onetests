<template>
  <div>
    <table class="table table-hover table-bordered">
      <thead>
        <tr>
          <th colspan="4">요청 정보</th>
          <th colspan="3">응답 정보</th>
          <th rowspan="2">실행자</th>
          <th rowspan="2">수행 일시</th>
        </tr>
        <tr>
          <th>메소드</th>
          <th>URL</th>
          <th>쿼리 개수</th>
          <th>헤더 개수</th>
          <th>상태</th>
          <th>헤더 개수</th>
          <th>바디 길이</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="call in revCalls" @click="detail(call.id)">
          <td>{{call.request.method}}</td>
          <td>{{call.request.url}}</td>
          <td>{{keysLength(call.request.queries)}}</td>
          <td>{{keysLength(call.request.headers)}}</td>
          <td>{{call.response.statusCode}} {{call.response.statusMessage}}</td>
          <td>{{keysLength(call.response.headers)}}</td>
          <td>{{call.response.headers['Content-Length'] || call.response.body.length}}</td>
          <td>{{call.createdBy || '아무게'}}</td>
          <td>{{call.createdDate | formatDate}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
  props: ["calls"],
  computed: {
    revCalls() {
      return this.calls.slice().reverse();
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

<style scoped>
.table > thead > tr > th, td {
  text-align: center;
  vertical-align: middle;
}

.table > thead > tr > th {
  background-color: #f5f5f5;
}
</style>
