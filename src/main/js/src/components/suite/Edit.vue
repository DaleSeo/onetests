<template>
  <div class="ui container">
    <Title icon="cubes" title="Test Suite" subTitle="일괄 검증"/>
    <Steps :step="step" />
    <div class="ui segment">
      <component :is="view"
        :suite="suite"
        @updateStep="updateStep"
      />
    </div>
  </div>
</template>
<script>
import Steps from './Steps.vue'
import Form0 from './Form0.vue'
import Form1 from './Form1.vue'

export default {
  components: {Steps, Form0, Form1},
  data () {
    return {
      step: 1,
      suite: {
        title: '',
        description: '',
        serviceId: '5912a299ec46ff6c417a9482',
        hostId: '',
        cases: []
      }
    }
  },
  computed: {
    view () {
      switch (this.step) {
        case 0: return Form0
        case 1: return Form1
        default: return Form0
      }
    }
  },
  methods: {
    updateStep (step) {
      this.step = step
    }
  }
}
</script>
