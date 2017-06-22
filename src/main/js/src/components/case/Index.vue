<template>
  <div class="ui container">
    <Title icon="cube" title="Test Case" subTitle="개별 검증"/>
    <List/>
  </div>
</template>
<script>
import List from './List.vue'

export default {
  components: {List},
  data () {
    return {
      id: ''
    }
  },
  methods: {
    create () {
      window.location.href = '/cases/new'
    }
  }
}
</script>
