<template>
  <div class="ui container">
    <h3 class="ui dividing header">
      <i class="cube icon"></i>
      <div class="content">
        Test Case
        <div class="sub header">
          개별 검증 상세
        </div>
      </div>
    </h3>

    <div style="text-align: right">
      <button class="ui mini primary labeled icon button" @click="run">
        <i class="play icon"></i>
        실행
      </button>
      <button class="ui mini labeled icon button" @click="list">
        <i class="list icon"></i>
        목록
      </button>
      <button class="ui mini negative labeled icon button" @click="del">
        <i class="trash icon"></i>
        삭제
      </button>
    </div>

    <h5 class="ui horizontal divider header">
      <i class="browser icon"/>기본 정보
    </h5>

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
          <td>호출 시간</td>
          <td>{{cas.createdDate | formatDate}}</td>
        </tr>
        <tr>
          <td>호출자</td>
          <td>{{cas.createdBy | userName}}</td>
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
          <td><Method :method="cas.request.method"/></td>
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
  </div>
</template>

<script>
import caseSvc from '../../services/caseSvc'
import utils from '../../services/utils'

export default {
  props: ["id"],
  data() {
    return {
      cas: {
        request: {},
        response: {},
        service: {}
      }
    }
  },
  created () {
    this.detail()
  },
  methods: {
    detail () {
      caseSvc.detail(this.id)
        .then(cas => this.cas = cas)
    },
    list () {
      window.location.href = '/cases'
    },
    run () {
      window.location.href = '/console'
      // window.location.href = '/console/' + this.id
    },
    del () {
      caseSvc.remove(this.id)
        .then(toastr.success('테스트 케이스가 삭제되었습니다.'))
        .then(this.list)
    },
    methodClass (method) {
      return utils.methodClass(method)
    }
  }
}
</script>

<style scoped>
.body {
  background-color: white;
  margin-bottom: 0px;
}

.dl-horizontal {
  margin-bottom: 0px;
}
</style>
