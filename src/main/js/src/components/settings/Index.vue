<template>
  <div class="ui container">
    <Title icon="settings" title="Settings" subTitle="환경 설정"/>
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
