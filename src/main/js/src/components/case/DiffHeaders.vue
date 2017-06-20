<template>
  <div class="ui two column internally celled grid">
    <div class="column">
      <h5 class="ui header">기대 헤더</h5>
      <table class="ui definition table">
        <tbody>
          <tr v-for="(val, key) in result.expected.headers">
            <td>{{key}}</td>
            <td>{{val}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="column">
      <h5 class="ui header">실제 헤더</h5>
      <table class="ui definition table">
        <tbody>
          <tr v-for="(val, key) in result.actual.headers">
            <td>{{key}}</td>
            <td>{{val}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    result: {
      type: Object,
      default: {
        expected: {},
        actual: {}
      }
    }
  }
}
</script>
