<template>
  <div class="buttons text-right">
      <button type="button" class="btn btn-primary" @click="callApi"><i class="fa fa-play"/> 호출</button>
      <button type="button" class="btn btn-danger" @click="cancel"><i class="fa fa-undo"/> 취소</button>
      <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#share"><i class="fa fa-share-alt"/> 공유</button>
      <button type="button" class="btn btn-success" data-toggle="modal" data-target="#curl"><i class="fa fa-code"/> Curl로 변환</button>
      <button type="button" class="btn btn-info" @click="register"><i class="fa fa-pencil"/> 테스트케이스로 등록</button>
  </div>
</template>

<script>
export default {
  methods: {
    callApi () {
      this.$emit('callApi')
    },
    cancel () {
      this.$emit('cancel')
    },
    register () {
      toastr.info('구현 중입니다')
    }
  }
}
</script>

<style>
  .buttons {
    margin-bottom: 18px;
  }
</style>
