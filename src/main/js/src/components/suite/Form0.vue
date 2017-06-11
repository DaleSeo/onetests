<template>
  <form class="ui small form" @submit.prevent @reset.prevent>
    <div class="field required">
      <label for="title">제목</label>
      <input id="title" type="text" required v-model="suite.title"/>
    </div>

    <div class="field">
      <label for="description">설명</label>
      <textarea id="description" rows="3" v-model="suite.description"/>
    </div>

    <div class="fields">
      <div class="eight wide required field">
        <label for="serviceDropdown">서비스</label>
        <ServiceDropdown v-model="suite.serviceId" />
      </div>
      <div class="eight wide required field">
        <label for="hostDropdown">호스트</label>
        <HostDropdown v-model="suite.hostId" :serviceId="suite.serviceId" />
      </div>
    </div>

    <div style="text-align: right">
      <button type="button" class="ui labeled icon button" @click="next">
        <i class="step forward icon"/>다음
      </button>
    </div>
  </form>
</template>

<script>
export default {
  props: ['suite'],
  methods: {
    next() {
      this.$emit('changeStep', 1)
    }
  }
}
</script>
