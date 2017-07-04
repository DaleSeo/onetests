<template>
  <div class="ui container">
    <Title icon="cubes" title="Test Suite" subTitle="일괄 검증"/>
    <Steps :step="step" />
    <div class="ui segment">
      <component :is="view"
        :suite="suite"
        @changeStep="changeStep"
      />
    </div>
    <!-- <pre>{{suite}}</pre> -->
  </div>
</template>
<script>
import Steps from './Steps.vue'
import Form0 from './Form0.vue'
import Form1 from './Form1.vue'
import Form2 from './Form2.vue'

export default {
  components: {Steps, Form0, Form1},
  props: ['id'],
  data () {
    return {
      step: 0,
      suite: {
        id: '',
        title: '',
        description: '',
        serviceId: '',
        caseIds: []
      }
    }
  },
  computed: {
    view () {
      switch (this.step) {
        case 0: return Form0
        case 1: return Form1
        case 2: return Form2
        default: return Form0
      }
    }
  },
  created () {
    this.suite.id = this.id
    this.step = this.id ? 2 : 0
  },
  methods: {
    changeStep (step) {
      this.step = step
    }
  }
}
</script>
