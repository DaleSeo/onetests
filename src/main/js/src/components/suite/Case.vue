<template>
  <li class="list-group-item">
    <span class="label" :class="methodClass(request.method)">{{request.method}}</span>
    &nbsp;<em>{{request.url}}</em>
    <pre v-if="request.quries" v-text="request.quries"/>
    <pre v-if="request.headers" v-text="request.headers"/>
    <pre v-if="request.body" v-text="request.body"/>
  </li>
</template>

<script>
import utils from '../../services/utils'

export default {
  props: ["request"],
  methods: {
    methodClass (method) {
      return utils.methodClass(method)
    }
  }
}
</script>

<style lang="css">
</style>
