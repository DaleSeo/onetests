<template>
  <table class="ui attached definition table" v-if="cas.response">
    <tbody>
      <tr>
        <td class="two wide column">응답 상태</td>
        <td>{{cas.response.statusCode}} {{cas.response.statusMessage}}</td>
      </tr>
      <tr>
        <td>헤더</td>
        <td>
          <dl>
            <div v-for="(val, key) in cas.response.headers">
              <dt>{{key}}</dt>
              <dd>{{val}}</dd>
            </div>
          </dl>
        </td>
      </tr>
      <tr>
        <td>바디</td>
        <td>
          <pre class="body">{{cas.response.body}}</pre>
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
