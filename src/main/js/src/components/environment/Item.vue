<template>
  <div class="ui message" @click="$emit('pick')">
    <div class="header">
      <div v-if="environment.code" class="ui label">{{environment.code}}</div>
      {{environment.host}}
    </div>
    <p>
      <span>{{environment.description}}&nbsp;</span>
      <span style="float: right">
        <i class="calendar icon"/>{{environment.createdDate | formatDate}}
      </span>
    </p>
  </div>
</template>

<script>
export default {
  props: ['environment']
}
</script>
