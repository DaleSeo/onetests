<template>
  <button class="list-group-item" @click="pick">
    <h5 class="list-group-item-heading">
      {{api.title}}
    </h5>
    <p class="list-group-item-text">
      <span class="label" :class="className(api.method)">{{api.method}}</span>
      &nbsp;<em>{{api.path.slice(0, 50)}}</em>
    </p>
  </button>
</template>

<script>
export default {
  props: ['api'],
  methods: {
    className (method) {
      switch (method) {
        case 'GET': return 'label-success'
        case 'POST': return 'label-info'
        case 'PUT': return 'label-warning'
        case 'DELETE': return 'label-danger'
        default: return 'label-primary'
      }
    },
    pick () {
      this.$emit('pick', this.api)
    }
  }
}
</script>
