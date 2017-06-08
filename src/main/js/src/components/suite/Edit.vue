<template>
  <div class="ui container">
    <Title icon="cubes" title="Test Suite" subTitle="일괄 검증"/>
    <Steps :step="step" />
    <div class="ui segment">
      <Form0 :basics="form0" v-if="step === 0" />
      <Form1 v-if="step === 1" />
    </div>
    <pre>
      {{form0}}
    </pre>
  </div>
</template>
<script>
import Steps from './Steps.vue'
import Form0 from './Form0.vue'

export default {
  components: {Steps, Form0},
  data () {
    return {
      step: 0,
      form0: {
        title: '',
        description: '',
        serviceId: '',
        hostId: ''
      },
      form1: {}
    }
  }
}
</script>
