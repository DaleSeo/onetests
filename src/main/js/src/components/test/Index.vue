<template>
  <div class="ui text container">
    Host: <input v-model="host"/>
    <HostInput v-model="host"/>
  </div>
</template>

<script>
export default {
  data () {
    return {
      host: '',
      service: null
    }
  }
}
</script>
