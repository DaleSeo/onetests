<template>
  <div>
    <div class="ui attached message">
      <div class="header">
        서비스 상세 <small>({{service.id}})</small>
      </div>
    </div>
    <form class="ui form attached fluid segment" @submit.prevent="save" @reset="cancel">
      <div class="disabled field">
        <label for="id">ID</label>
        <input id="id" type="text" readonly v-model="service.id"/>
      </div>
      <div class="required field">
        <label for="code">코드</label>
        <input id="code" type="text" required v-model="service.code"/>
      </div>
      <div class="required field">
        <label for="name">이름</label>
        <input id="name" type="text" required v-model="service.name"/>
      </div>
      <div class="field">
        <label for="description">설명</label>
        <textarea id="description" v-model="service.description"/>
      </div>
      <div style="text-align: right">
        <button type="submit" class="ui tiny button">저장</button>
        <button type="reset" class="ui tiny button">취소</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  props: ['service'],
  methods: {
    save () {
      console.log('#save:', this.service)
      this.$emit('save')
    },
    cancel () {
      this.$emit('cancel')
    }
  }
}
</script>
