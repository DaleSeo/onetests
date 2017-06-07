<template>
  <table class="ui attached definition table">
    <tbody>
      <tr>
        <td class="two wide column">메서드</td>
        <td><Method :method="cas.request.method"/></td>
      </tr>
      <tr>
        <td>호스트</td>
        <td>{{cas.request.host}}</td>
      </tr>
      <tr>
        <td>패스</td>
        <td>{{cas.request.path}}</td>
      </tr>
      <tr>
        <td>쿼리</td>
        <td>
          <dl>
            <div v-for="(val, key) in cas.request.queries">
              <dt>{{key}}</dt>
              <dd>{{val}}</dd>
            </div>
          </dl>
        </td>
      </tr>
      <tr>
        <td>헤더</td>
        <td>
          <dl>
            <div v-for="(val, key) in cas.request.headers">
              <dt>{{key}}</dt>
              <dd>{{val}}</dd>
            </div>
          </dl>
        </td>
      </tr>
      <tr>
        <td>바디</td>
        <td>
          <pre class="body">{{cas.request.body}}</pre>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  props: ['cas']
}
</script>
