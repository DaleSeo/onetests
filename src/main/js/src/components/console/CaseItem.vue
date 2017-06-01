<template>
  <button class="list-group-item" @click="pick">
    <h5 class="list-group-item-heading">
      {{cas.title}}
    </h5>
    <p class="list-group-item-text">
      <span class="label" :class="className(cas.request.method)">{{cas.request.method}}</span>
      &nbsp;<em>{{cas.request.path && cas.request.path.slice(0, 50)}}</em>
    </p>
  </button>
</template>

<script>
export default {
  props: ['cas'],
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
      this.$emit('pick', this.cas)
    }
  }
}
</script>
