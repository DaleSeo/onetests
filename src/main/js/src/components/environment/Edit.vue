<template>
  <div class="ui tiny form segment">
    <div class="fields">
      <div class="six wide required field">
        <label>코드</label>
        <input v-model="environment.code"/>
      </div>
      <div class="ten wide required field">
        <label>제목</label>
        <input v-model="environment.title"/>
      </div>
    </div>
    <div class="field">
      <label>호스트</label>
      <input v-model="environment.host"/>
    </div>
    <div class="field">
      <label>변수</label>
      <Variables :variables="environment.variables"/>
    </div>
    <div class="field">
      <label>설명</label>
      <textarea v-model="environment.description"/>
    </div>
  </div>
</template>

<script>
import Variables from './Variables.vue'

export default {
  components: {Variables},
  props: ['environment']
}
</script>
