<template>
  <div class="container">
    <h3>
      <i class="fa fa-cubes"/> <b>Test Suite</b> <small>일괄 검증</small>
      <div class="pull-right">
        <button class="btn btn-primary" @click="newSuite">
          <i class="fa fa-edit"/> 신규 검증
        </button>
      </div>
    </h3>
    <hr/>
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
