<template>
  <div class="ui right aligned basic segment">
      <button type="button" class="ui labeled icon small button" @click="callApi">
        <i class="play icon"/> 호출
      </button>
      <button type="button" class="ui labeled icon small button" @click="cancel">
        <i class="undo icon"/> 취소
      </button>
      <button type="button" class="ui labeled icon small button" data-toggle="modal" data-target="#curl">
        <i class="code icon"/> CURL
      </button>
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
