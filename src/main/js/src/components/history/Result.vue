<template>
  <div>
    <br/>
    <h5 class="ui horizontal divider header">
      <i class="browser icon"/>검증 결과
    </h5>

    <table class="ui definition table">
      <tbody>
        <tr>
          <td class="two wide column">통과 여부</td>
          <td>{{result.passed}}</td>
        </tr>
        <tr>
          <td>상태 일치</td>
          <td>{{result.statusEqual}}</td>
        </tr>
        <tr>
          <td>헤더 일치</td>
          <td>{{result.headersEqual}}</td>
        </tr>
        <tr>
          <td>바디 일치</td>
          <td>{{result.bodyEqual}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: ['result']
}
</script>
