<template>
  <div class="ui message" @click="pick">
    <div class="header">
      <div class="ui label">{{service.code}}</div> {{service.name}}
    </div>
    <p>
      {{service.description}}
      <span style="float: right">
        <i class="calendar icon"/>{{service.createdDate | formatDate}}
      </span>
    </p>
  </div>
</template>

<script>
export default {
  props: ['service'],
  methods: {
    pick () {
      this.$emit('pick', this.service)
    }
  }
}
</script>
