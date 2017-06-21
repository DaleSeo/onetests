<template>
  <div class="ui message" @click="pick">
    <div class="header">
      <div class="ui label">{{host.service && host.service.code}}</div>
      <div v-if="host.environment" class="ui label">{{host.environment}}</div> 
      {{host.title}}
    </div>
    <p>
      {{host.baseUrl}}
      <span style="float: right">
        <i class="calendar icon"/>{{host.createdDate | formatDate}}
      </span>
    </p>
  </div>
</template>

<script>
export default {
  props: ['host'],
  methods: {
    pick () {
      this.$emit('pick', this.host)
    }
  }
}
</script>
