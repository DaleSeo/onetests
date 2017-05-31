<template>
  <div class="row">
    <div class="col-md-6">
      <ExclusionsHeaders/>
    </div>
    <div class="col-md-6">
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
