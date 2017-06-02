<template>
  <span>
    <div class="ui label" :class="color">
      {{method}}
    </div>
  </span>
</template>

<script>
export default {
  props: ["method"],
  computed: {
    color () {
      switch (this.method) {
        case 'GET': return 'green'
        case 'POST': return 'blue'
        case 'PUT': return 'yellow'
        case 'DELETE': return 'red'
        default: return ''
      }
    }
  }
}
</script>
