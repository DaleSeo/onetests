<template>
  <div>
    <div class="title">
      <RequestLine :method="cas.request.method" :path="cas.request.path"/>
      {{cas.title}}
    </div>
    <div class="content">
      <button class="ui right floated mini circular icon button" @click="pick">
        <i class="chevron right icon"/>
      </button>
      <div v-if="cas.request.queries && Object.keys(cas.request.queries) > 0">{{cas.request.queries}}</div>
      <div v-if="cas.request.body">{{cas.request.body}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['cas'],
  methods: {
    pick () {
      this.$emit('pick', this.cas)
    }
  }
}
</script>

<style scoped>
.content {
  white-space: pre-wrap;
}
</style>
