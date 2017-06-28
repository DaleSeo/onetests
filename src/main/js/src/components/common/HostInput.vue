<template>
  <div>
    <div class="ui fluid left icon input" :class="{loading}">
      <input list="hosts" placeholder="호스트를 입력하세요. (http://~)" :value="value" @input="$emit('input', $event.target.value)"/>
      <i class="world icon"/>
    </div>
    <datalist id="hosts">
      <option :value="host" v-for="host in hosts"/>
    </datalist>
  </div>
</template>

<script>
export default {
  props: ['value'],
  data () {
    return {
      hosts: [],
      loading: false
    }
  },
  created () {
    this.fetch()
  },
  methods: {
    fetch () {
      this.loading = true
      this.$http.get('/api/environments')
        .then(res => res.body._embedded.environments)
        .then(environments => {
          console.log(environments)
          return environments
        })
        .then(environments => this.hosts = environments.map(environment => environment.host))
        .catch(err => {
          console.error(err)
          throw err
        })
        .catch(err => toastr.error('호스트 조회 실패'))
        .then(_ => this.loading = false)
    }
  }
}
</script>
