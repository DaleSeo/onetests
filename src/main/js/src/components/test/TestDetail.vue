<template>
  <div class="container">
    <hr/>
    <TabSum :result="test.result" v-if="test.result"/>
    <br/>
    <div clsss="row">
      <ul class="nav nav-tabs">
        <li :class="{active: tab === 'req'}" @click="tab = 'req'"><a href="#req"><b>요청 비교</b></a></li>
        <li :class="{active: tab === 'res'}" @click="tab = 'res'"><a href="#res"><b>응답 비교</b></a></li>
        <li :class="{active: tab === 'back'}" @click="tab = 'back'">
          <a @click="unpick"><i class="fa fa-arrow-circle-left"></i></a>
        </li>
      </ul>
    </div>
    <br/>
    <TabReq :reqA="test.reqA" :reqB="test.reqB" v-if="test.reqA && tab === 'req'"/>
    <TabRes :resA="test.resA" :resB="test.resB" v-if="test.reqB && tab === 'res'"/>
  </div>
</template>

<script>
import TabSum from './TabSum.vue'
import TabReq from './TabReq.vue'
import TabRes from './TabRes.vue'

import db from '../../services/database'

export default {
  props: ['test', 'suiteId'],
  components: {TabSum, TabReq, TabRes},
  data () {
    return {
      tab: 'res'
    }
  },
  methods: {
    unpick () {
      this.$emit('unpick')
    }
  }
}
</script>
