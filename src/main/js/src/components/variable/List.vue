<template>
  <ListForm
    title="바디 전역 예외"
    :variables="variables"
    :loading="loading"
    @add="add"
    @del="del"
  />
</template>

<script>
export default {
  props: ['variables']
}
</script>
