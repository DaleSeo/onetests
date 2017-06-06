<template>
  <div class="ui container">
    <Title icon="cubes" title="Test Suite" subTitle="일괄 검증"/>
    <SuiteForm/>
  </div>
</template>
<script>
import SuiteForm from './SuiteForm.vue'

export default {
  components: { SuiteForm }
}
</script>
