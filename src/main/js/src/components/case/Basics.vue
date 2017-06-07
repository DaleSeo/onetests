<template>
  <table class="ui definition table">
    <tbody>
      <tr>
        <td class="two wide column">케이스 ID</td>
        <td>{{cas.id}}</td>
      </tr>
      <tr>
        <td>서비스 코드</td>
        <td>{{cas.service.code}}</td>
      </tr>
      <tr>
        <td>생성 일시</td>
        <td>{{cas.createdDate | formatDate}}</td>
      </tr>
      <tr>
        <td>생성자</td>
        <td>{{cas.createdBy | userName}}</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  props: ['cas']
}
</script>
