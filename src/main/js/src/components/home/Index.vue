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
        <Statistics/>
      </div>
      <div class="ui vertical center aligned segment">
        <Ranking/>
      </div>
      <div class="ui vertical center aligned segment">
        <Quotes/>
      </div>
    </div>
  </div>
</template>

<script>
import Jumbotron from './Jumbotron.vue'
import Ranking from './Ranking.vue'
import Statistics from './Statistics.vue'
import Notice from './Notice.vue'
import Quotes from './Quotes.vue'

export default {
  components: {Jumbotron, Ranking, Statistics, Notice, Quotes}
}
</script>

<style scoped>
.tight {
  padding: 0em;
}
.jumbotron.segment {
  min-height: 350px;
}
</style>
