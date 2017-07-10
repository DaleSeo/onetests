<template>
  <div>
    <div class="ui inverted vertical jumbotron center aligned segment">
      <Jumbotron/>
    </div>
    <div class="ui container">
      <div class="ui vertical center aligned segment">
        <Notice/>
      </div>
      <div class="ui vertical center aligned segment">
        <Stats/>
      </div>
      <!--<div class="ui vertical center aligned segment">-->
        <!--<Ranking/>-->
      <!--</div>-->
      <div class="ui vertical center aligned segment">
        <Quotes/>
      </div>
    </div>
  </div>
</template>

<script>
import Jumbotron from './Jumbotron.vue'
//import Ranking from './Ranking.vue'
import Stats from './Stats.vue'
import Notice from './Notice.vue'
import Quotes from './Quotes.vue'

export default {
  components: {Jumbotron, Stats, Notice, Quotes}
}
</script>

<style scoped>
.jumbotron.segment {
  min-height: 350px;
}
</style>
