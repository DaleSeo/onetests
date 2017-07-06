<template>
  <div>
    <div class="ui attached message">
      <div class="header">
        호스트 상세
      </div>
    </div>
    <form class="ui tiny form attached fluid segment" @submit.prevent="$emit('save')" @reset="$emit('cancel')">
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
      <div style="text-align: right">
        <button type="submit" class="ui tiny button">저장</button>
        <button type="reset" class="ui tiny button">취소</button>
      </div>
    </form>
  </div>
</template>

<script>
import Variables from './Variables.vue'

export default {
  components: {Variables},
  props: ['environment']
}
</script>
