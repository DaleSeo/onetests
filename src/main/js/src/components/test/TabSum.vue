<template>
  <div class="row">
    <div class="col-md-2 col-md-offset-3 text-center">
      <b>상태 {{result.statusEqual ? '일치!' : '불일치!'}}</b>
    </div>
    <div class="col-md-2 text-center">
      <b>헤더 {{result.headersEqual ? '일치!' : '불일치!'}}</b>
    </div>
    <div class="col-md-2 text-center">
      <b>바디 {{result.bodyEqual ? '일치!' : '불일치!'}}</b>
    </div>
  </div>
</template>

<script>
export default {
  props: ['result']
}
</script>

<style scoped>
.dl-horizontal {
  margin-bottom: 0px;
}
</style>
