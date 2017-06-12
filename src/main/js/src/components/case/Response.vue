<template>
  <div>
    <div>
      <button class="ui right floated icon labeled small button" @click="record">
        <i class="record icon"/>레코딩
      </button>
    </div>
    <div>&nbsp;</div>
    <table class="ui definition table" v-if="cas.response">
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
    <div class="ui info message" v-else>
      등록된 기대 응답 정보가 없습니다.
    </div>
  </div>
</template>

<script>
export default {
  props: ['cas'],
  methods: {
    record () {
      console.log('#record')
    }
  }
}
</script>
