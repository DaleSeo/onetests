<template>
  <span>
    <div :class="className">
      {{method}}
    </div>
  </span>
</template>

<script>
export default {
  props: ['method', 'type'],
  data () {
    return {
      ribbon: 'ribbon'
    }
  },
  computed: {
    className () {
      let classNames = ['ui']
      if (this.type === 'ribbon') {
        classNames.push('ribbon')
      }
      classNames.push(this.color())
      classNames.push('label')
      return classNames.join(' ')
    }
  },
  methods: {
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
