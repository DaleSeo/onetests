<template>
  <div class="ui two column grid">
    <div class="column">
      <ExclusionsHeaders/>
    </div>
    <div class="column">
      <ExclusionsBody/>
    </div>
  </div>
</template>

<script>
import ExclusionsHeaders from './ExclusionsHeaders.vue'
import ExclusionsBody from './ExclusionsBody.vue'

import db from '../../services/database'

export default {
  components: {ExclusionsHeaders, ExclusionsBody}
}
</script>
