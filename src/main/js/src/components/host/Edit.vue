<template>
  <div>
    <div class="ui attached message">
      <div class="header">
        호스트 상세
      </div>
    </div>
    <form class="ui form attached fluid segment" @submit.prevent="save" @reset="cancel">
      <div class="disabled field">
        <label for="id">ID</label>
        <input id="id" type="text" readonly :value="host.id"/>
      </div>
      <div class="disabled field">
        <label for="serviceId">Service ID</label>
        <input id="serviceId" type="text" readonly :value="host.serviceId"/>
      </div>
      <div class="required field">
        <label for="title">환경</label>
        <select class="ui fluid dropdown" required v-model="host.environment">
          <option value="LOCAL">로컬</option>
          <option value="DEV">개발</option>
          <option value="QA">검증</option>
          <option value="STG">스테이징</option>
          <option value="PROD">상용</option>
        </select>
      </div>
      <div class="required field">
        <label for="title">이름</label>
        <input id="title" type="text" required v-model="host.title"/>
      </div>
      <div class="required field">
        <label for="baseUrl">URL</label>
        <input id="baseUrl" type="text" required v-model="host.baseUrl" placeholder="http:// 또는 https:// 로 시작할 것"/>
      </div>
      <div class="field">
        <label for="description">설명</label>
        <textarea id="description" v-model="host.description"/>
      </div>
      <div class="disabled field">
        <label for="createdDate">등록일</label>
        <input id="createdDate" type="text" readonly :value="host.createdDate | formatDate"/>
      </div>
      <div class="disabled field">
        <label for="createdBy">등록자</label>
        <input id="createdBy" type="text" readonly :value="host.createdBy | userName"/>
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
  props: ['host'],
  methods: {
    save () {
      console.log('#save:', this.host)
      this.$emit('save')
    },
    cancel () {
      this.$emit('cancel')
    }
  }
}
</script>
