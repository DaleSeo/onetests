<template>
  <div>
    <table class="ui selectable very compact celled table">
      <thead class="center aligned">
        <tr>
          <th colspan="5">요청 정보</th>
          <th colspan="3">응답 정보</th>
          <th rowspan="2">호출 일시</th>
        </tr>
        <tr>
          <th>메소드</th>
          <th>호스트</th>
          <th>패스</th>
          <th>쿼리 개수</th>
          <th>헤더 개수</th>
          <th>상태</th>
          <th>헤더 개수</th>
          <th>바디 길이</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="call in calls" @click="detail(call.id)">
          <td><Method :method="call.request.method"/></td>
          <td>{{call.request.host}}</td>
          <td>{{call.request.path | limitLength}}</td>
          <td>{{keysLength(call.request.queries)}}</td>
          <td>{{keysLength(call.request.headers)}}</td>
          <td>{{call.response.statusCode}} {{call.response.statusMessage}}</td>
          <td>{{keysLength(call.response.headers)}}</td>
          <td>{{call.response.headers['Content-Length'] || call.response.body.length}}</td>
          <td>{{call.createdDate | formatDate}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
  props: ["calls"],
  methods: {
    detail (id) {
      window.location.href = `/calls/${id}`
    },
    keysLength (object) {
      return object ? Object.keys(object).length : 0
    }
  }
}
</script>
