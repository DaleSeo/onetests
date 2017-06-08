<template>
  <form class="ui small form" @submit.prevent="createSuite" @reset.prevent="reset">
    <div class="field required">
      <label for="title">제목</label>
      <input id="title" type="text" required v-model="basics.title"/>
    </div>

    <div class="field">
      <label for="description">설명</label>
      <textarea id="description" rows="3" v-model="basics.description"/>
    </div>

    <div class="fields">
      <div class="eight wide required field">
        <label for="serviceDropdown">서비스</label>
        <ServiceDropdown v-model="basics.serviceId" />
      </div>
      <div class="eight wide required field">
        <label for="hostDropdown">호스트</label>
        <HostDropdown v-model="basics.hostId" :serviceId="basics.serviceId" />
      </div>
    </div>

    <div class="right aligned field">
      <button type="button" class="ui right labeled icon button">
        <i class="pointing right icon"/>다음
      </button>
    </div>
  </form>
</template>

<script>
export default {
  props: ['basics']
}
</script>
