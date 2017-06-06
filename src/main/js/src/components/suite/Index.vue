<template>
  <div class="ui container">
    <Title icon="cubes" title="Test Suite" subTitle="일괄 검증"/>
    <div style="text-align: right">
      <button class="ui mini primary labeled icon button" @click="newSuite">
        <i class="plus icon"/>신규 검증
      </button>
    </div>
    <br/>
    <List/>
  </div>
</template>

<script>
import List from './List.vue'

export default {
  components: { List },
  methods: {
    newSuite () {
      window.location.href = '/suites/new'
    }
  }
}
</script>
