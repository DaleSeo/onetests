<template>
  <div id="pageHeader" class="ui stackable inverted menu">
    <div class="ui container">
      <a href="/" class="header item">
        <h1 class="ui teal inverted header">ONE tests</h1>
      </a>
      <a href="/" class="red item">
        <i class="home icon"/>Home
      </a>
      <a href="/console" class="item">
        <i class="terminal icon"/>Console
      </a>
      <a href="/cases" class="item">
        <i class="cube icon"/>Cases
      </a>
      <a href="/suites" class="item">
        <i class="cubes icon"/>Suites
      </a>
      <a href="/calls" class="item">
        <i class="history icon"/>History
      </a>
      <a href="/dashboard" class="item">
        <i class="dashboard icon"/>Dashboard
      </a>
      <!-- <div class="item">
        <CaseSearch :fluid="true"/>
      </div> -->
      <div class="right menu">
        <div class="ui dropdown item" v-if="authenticated">
          <i class="user icon"/>Profile<i class="dropdown icon"/>
          <div class="menu">
            <a href="/profile" class="item">
              <i class="user icon"/>My Page
            </a>
            <a href="/settings" class="item">
              <i class="settings icon"/>Settings
            </a>
            <a href="/admin" class="item">
              <i class="doctor icon"/>Admin
            </a>
            <a href="/logout" class="item">
              <i class="sign out icon"/>Logout
            </a>
          </div>
        </div>
        <div class="item" v-else>
          <a href="/login" class="ui inverted teal button">
            로그인
          </a>
          &nbsp;&nbsp;&nbsp;
          <a href="/signup" class="ui inverted button">
            회원가입
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    authenticated: {
      type: Boolean,
      default: false
    }
  },
  mounted () {
    // console.log('#pageHeader#mounted')
    this.dropdown()
  },
  updated () {
    // console.log('#pageHeader#updated')
    this.dropdown()
  },
  methods: {
    dropdown () {
      $('#pageHeader .ui.dropdown').dropdown()
    }
  }
}
</script>
