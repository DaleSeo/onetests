<template>
  <div class="ui text container">
    <pre>Environment: {{environment && environment.id}}</pre>
    <div class="ui divider"/>
    <EnvironmentDropdown v-model="environment"/>
  </div>
</template>

<script>
import EnvironmentDropdown from '../common/EnvironmentDropdown.vue'

export default {
  components: {EnvironmentDropdown},
  data () {
    return {
      environment: null
    }
  }
}
</script>
