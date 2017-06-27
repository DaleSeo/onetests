<template>
  <div class="ui message" @click="pick">
    <div class="header">
      <div class="ui label">{{environment.service && environment.service.code}}</div>
      <div v-if="environment.code" class="ui label">{{environment.code}}</div>
      {{environment.title}}
    </div>
    <p>
      {{environment.description}}
      <span style="float: right">
        <i class="calendar icon"/>{{environment.createdDate | formatDate}}
      </span>
    </p>
    <p>
      {{environment.variables}}
    </p>
  </div>
</template>

<script>
export default {
  props: ['environment'],
  methods: {
    pick () {
      this.$emit('pick')
    }
  }
}
</script>
