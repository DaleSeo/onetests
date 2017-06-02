<template>
  <div class="container">
    <Title icon="history" title="History" subTitle="호출 이력 상세"/>

    <div style="text-align: right">
      <button class="ui mini teal labeled icon button" @click="run">
        <i class="play icon"/>실행
      </button>
      <button class="ui mini labeled icon button" @click="list">
        <i class="list icon"/>목록
      </button>
      <button class="ui mini negative labeled icon button" @click="del">
        <i class="trash icon"/>삭제
      </button>
    </div>

    <h5 class="ui horizontal divider header">
      <i class="browser icon"/>기본 정보
    </h5>

    <table class="ui definition table">
      <tbody>
        <tr>
          <td class="two wide column">Call ID</td>
          <td>{{call.id}}</td>
        </tr>
        <tr>
          <td>수행자</td>
          <td>{{call.createdBy || '아무게'}}</td>
        </tr>
        <tr>
          <td>수행 시간</td>
          <td>{{call.createdDate | formatDate}}</td>
        </tr>
      </tbody>
    </table>

    <h5 class="ui horizontal divider header">
      <i class="browser icon"/>요청 상세
    </h5>

    <table class="ui attached definition table">
      <tbody>
        <tr>
          <td class="two wide column">메서드</td>
          <td><Method :method="call.request.method"/></td>
        </tr>
        <tr>
          <td>호스트</td>
          <td>{{call.request.host}}</td>
        </tr>
        <tr>
          <td>패스</td>
          <td>{{call.request.path}}</td>
        </tr>
        <tr>
          <td>쿼리</td>
          <td>
            <dl>
              <div v-for="(val, key) in call.request.queries">
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
              <div v-for="(val, key) in call.request.headers">
                <dt>{{key}}</dt>
                <dd>{{val}}</dd>
              </div>
            </dl>
          </td>
        </tr>
        <tr>
          <td>바디</td>
          <td>
            <pre class="body">{{call.request.body}}</pre>
          </td>
        </tr>
      </tbody>
    </table>

    <h5 class="ui horizontal divider header">
      <i class="browser icon"/>응답 상세
    </h5>

    <table class="ui attached definition table">
      <tbody>
        <tr>
          <td class="two wide column">응답 상태</td>
          <td>{{call.response.statusCode}} {{call.response.statusMessage}}</td>
        </tr>
        <tr>
          <td>헤더</td>
          <td>
            <dl>
              <div v-for="(val, key) in call.response.headers">
                <dt>{{key}}</dt>
                <dd>{{val}}</dd>
              </div>
            </dl>
          </td>
        </tr>
        <tr>
          <td>바디</td>
          <td>
            <pre class="body">{{call.response.body}}</pre>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import callSvc from '../../services/callSvc'
import utils from '../../services/utils'

export default {
  props: ["id"],
  data() {
    return {
      call: {
        request: {},
        response: {}
      }
    }
  },
  created () {
    this.detail()
  },
  methods: {
    detail () {
      callSvc.detail(this.id)
        .then(call => this.call = call)
        .catch(toastr.error)
    },
    list () {
      window.location.href = '/calls'
    },
    run () {
      window.location.href = '/console?callId=' + this.id
    },
    del () {
      callSvc.remove(this.id)
        .then(this.list)
    },
    methodClass (method) {
      return utils.methodClass(method)
    }
  }
}
</script>
