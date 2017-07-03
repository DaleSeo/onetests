<template>
  <div id="EnvironmentDropdown" :class="classArr">
    <i :class="icon" class="icon"/>
    <span class="text">{{value ? value.code : '환경 옵션'}}</span>
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
const DEFAULT_CLASSES = ['ui', 'dropdown']

export default {
  props: {
    value: Object,
    fluid: Boolean,
    readonly: Boolean,
    size: String,
    look: {
      type: String,
      default: 'filter'
    }
  },
  data () {
    return {
      environments: [],
      menuShown: false,
    }
  },
  computed: {
    classArr () {
      let classes

      switch (this.look) {
        case 'selection':
          classes = DEFAULT_CLASSES.concat(['selection'])
          break;
        case 'button':
          classes = DEFAULT_CLASSES.concat([])
          break;
        case 'filter':
          classes = DEFAULT_CLASSES.concat(['pointing', 'icon', 'labeled', 'button'])
          break;
      }

      if (this.size) {
        classes.push(this.size)
      }

      if (this.fluid) {
        classes.push('fluid')
      }

      if (this.readonly) {
        classes.push('disabled')
      }

      return classes
    },
    icon () {
      switch (this.look) {
        case 'selection': return 'dropdown'
        case 'button': return ''
        case 'filter': return 'options'
      }
    }
  },
  created () {
    this.fetch()
  },
  mounted () {
    console.log('#mounted')
    $('#EnvironmentDropdown.ui.dropdown').dropdown()
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
