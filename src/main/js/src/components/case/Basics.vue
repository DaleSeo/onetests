<template>
  <div>
    <div class="ui grid">
      <div class="right aligned column">
        <button class="ui mini labeled icon button" @click="list">
          <i class="list icon"></i>
          목록
        </button>
        <button class="ui mini yellow labeled icon button" @click="edit">
          <i class="edit icon"></i>
          수정
        </button>
        <button class="ui mini negative labeled icon button" @click="del" v-if="false">
          <i class="trash icon"></i>
          삭제
        </button>
      </div>
    </div>
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
  </div>
</template>

<script>
export default {
  props: ['cas'],
  methods: {
    list () {
      window.location.href = '/cases'
    },
    del () {
      caseSvc.remove(this.cas.id)
        .then(toastr.success('테스트 케이스가 삭제되었습니다.'))
        .then(this.list)
    },
    edit () {
      window.location.href = '/cases/' + this.cas.id + '/edit'
    }
  }
}
</script>
