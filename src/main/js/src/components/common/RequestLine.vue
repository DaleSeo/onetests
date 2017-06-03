<template>
  <span>
    <div class="ui ribbon label" :class="color">
      {{method}} <div class="detail">{{shortPath}}</div>
    </div>
  </span>
</template>

<script>
export default {
  props: ["method", "path"],
  computed: {
    color () {
      switch (this.method) {
        case 'GET': return 'green'
        case 'POST': return 'blue'
        case 'PUT': return 'yellow'
        case 'DELETE': return 'red'
        default: return ''
      }
    },
    shortPath () {
      if (!this.path) {
        return ''
      }
      return this.path.length > 20 ? this.path.slice(0, 20) + '...' : this.path
    }
  }
}
</script>
