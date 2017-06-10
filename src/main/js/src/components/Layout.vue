<template>
  <div>
    <div class="ui inverted vertical segment" style="padding: 0">
      <PageHeader :authenticated="authenticated"/>
    </div>
    <div v-if="atHome">
      <router-view></router-view>
    </div>
    <div class="ui vertical segment" v-else>
      <br/>
      <router-view></router-view>
      <br/>
    </div>
    <div class="ui inverted vertical segment">
      <PageFooter/>
    </div>
  </div>
</template>

<script>
import PageHeader from './PageHeader.vue'
import PageFooter from './PageFooter.vue'

export default {
  components: { PageHeader, PageFooter },
  data () {
    return {
      authenticated: false
    }
  },
  computed: {
    atHome () {
      return this.$route.path === '/'
    }
  },
  created () {
    this.$http.get('/api/auth/isAuthenticated')
      .then(res => res.body)
      .then(authenticated => this.authenticated = authenticated)
      .catch(err => toastr.error('인증 여부 확인 실패'))
  }
}
</script>
