<template>
  <div class="container">
    <h3><i class="fa fa-cog"/> 설정</h3>
    <hr/>
    <Exclusions/>
  </div>
</template>

<script>
import Exclusions from './Exclusions.vue'

export default {
  components: {Exclusions}
}
</script>

<style scoped>
h3 {
  font-weight: bold;
}
</style>
