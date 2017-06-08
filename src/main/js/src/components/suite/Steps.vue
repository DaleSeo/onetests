<template>
  <div class="ui three tiny steps">
    <div class="step" :class="{active: isActive(0)}">
      <i class="info icon"></i>
      <div class="content">
        <div class="title">기본 정보 입력</div>
      </div>
    </div>
    <div class="step" :class="{active: isActive(1)}">
      <i class="block layout icon"></i>
      <div class="content">
        <div class="title">테스트 케이스 선택</div>
      </div>
    </div>
    <div class="step" :class="{active: isActive(2)}">
      <i class="play icon"></i>
      <div class="content">
        <div class="title">테스트 스위트 실행</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['step'],
  data () {
    return {
      steps: [
        {},
        {},
        {}
      ]
    }
  },
  methods: {
    isActive (idx) {
      return this.step === idx
    }
  }
}
</script>
