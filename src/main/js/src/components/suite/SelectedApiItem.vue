<template>
  <button type="button" class="list-group-item">
    <div class="row">
      <div class="col-sm-2 text-right">
        <button type="button" class="btn btn-danger" @click="unpick">
          <i class="fa fa-minus"/>
        </button>
      </div>
      <div class="col-sm-10">
        <h5 class="list-group-item-heading">
          {{api.title}}
        </h5>
        <p class="list-group-item-text">
          <span class="label" :class="className(api.method)">{{api.method}}</span>
          &nbsp;<em>{{api.url.slice(0, 50)}}</em>
        </p>
      </div>
    </div>
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
    unpick () {
      this.$emit('unpick', this.api)
    }
  }
}
</script>
