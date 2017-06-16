<template>
  <div class="ui two column grid">
    <div class="column">
      <table class="ui definition table">
        <tbody>
          <tr>
            <td class="four wide column">제목</td>
            <td>{{suite.title}}</td>
          </tr>
          <tr>
            <td>설명</td>
            <td>{{suite.description}}</td>
          </tr>
          <tr>
            <td>서비스</td>
            <td>{{suite.service ? suite.service.code : ''}}</td>
          </tr>
          <tr>
            <td>호스트</td>
            <td>{{suite.host ? suite.host.baseUrl : ''}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="column">
      <table class="ui definition table">
        <tbody>
          <tr>
            <td class="four wide column">등록 일시</td>
            <td>{{suite.createdDate | formatDate}}</td>
          </tr>
          <tr>
            <td>등록자</td>
            <td>{{suite.createdBy | userName}}</td>
          </tr>
          <tr>
            <td>변경 일시</td>
            <td>{{suite.lastModifiedDate | formatDate}}</td>
          </tr>
          <tr>
            <td>변경자</td>
            <td>{{suite.lastModifiedBy | userName}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: ['suite']
}
</script>
