<template>
  <div :id="result.id" class="ui fullscreen modal">
    <div class="ui header">
      <i class="law icon"></i>
      응답 상세 대조
    </div>
    <div class="content" v-if="result.expected && result.actual">
      <div class="ui two column grid">
        <div class="column">
          <h5 class="ui header">기대 상태</h5>
          {{result.expected.statusCode + ' ' + result.expected.statusMessage}}
        </div>
        <div class="column">
          <h5 class="ui header">실제 상태</h5>
          {{result.actual.statusCode + ' ' + result.actual.statusMessage}}
        </div>
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
        <div class="column">
          <h5 class="ui header">기대 바디</h5>
          <pre>{{result.expected.body}}</pre>
        </div>
        <div class="column">
          <h5 class="ui header">기대 바디</h5>
          <pre>{{result.actual.body}}</pre>
        </div>
        <div class="two column">
          <h5 class="ui header">대조</h5>
          <Delta :result="result"/>
        </div>
      </div>
    </div>
    <div class="actions">
      <div class="ui basic cancel button">
        <i class="remove icon"/>닫기
      </div>
    </div>
  </div>
</template>

<script>
import Delta from './Delta.vue'

export default {
  components: {Delta},
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
