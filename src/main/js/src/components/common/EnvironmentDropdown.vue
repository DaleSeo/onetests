<template>
  <div id="EnvironmentDropdown" class="ui pointing dropdown icon labeled button" :class="{fluid}">
    <i class="options icon"/>
    <span class="text">환경 옵션</span>
    <div class="menu" v-show="menuShown">
      <div class="item"
        @click="pick(null)"
      >
        환경 옵션
      </div>
      <div class="item"
        @click="pick(environment)"
        v-for="environment in environments"
      >
        {{environment.code}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['value', 'fluid'],
  data () {
    return {
      environments: [],
      menuShown: false
    }
  },
  created () {
    this.fetch()
  },
  watch () {
  },
  mounted () {
    $('#EnvironmentDropdown.ui.dropdown').dropdown()
    if (this.value) {
      let found = this.environments.find(env => env.id = this.value.id)
      this.pick(found)
    }
  },
  methods: {
    fetch () {
      this.loading = true
      this.$http.get('/api/environments')
        .then(res => res.body._embedded.environments)
        .then(environments => this.environments = environments)
        .catch(err => toastr.error('환경 목록 조회 실패'))
        .then(_ => this.loading = false)
    },
    toggleMenu () {
      this.menuShown = !this.menuShown
    },
    pick (environment) {
      this.$emit('input', environment)
    }
  }
}
</script>
