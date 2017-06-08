<template>
  <div>
    <div class="ui inverted vertical masthead center aligned segment">
      <div class="ui text container">
        <h1 class="ui inverted teal header">
          모두를 위한 하나의 테스트 플랫폼
        </h1>
        <h2>행복한 테스트를 경험해보세요!</h2>
        <div class="ui huge teal button">시작하기 <i class="right arrow icon"></i></div>
      </div>
    </div>
    <div class="ui vertical segment">
      <div class="ui center aligned container">
        <div class="ui category search">
          <div class="ui fluid massive icon input">
            <input class="prompt" type="text" placeholder="테스트 케이스 검색">
            <i class="search icon"></i>
          </div>
          <div class="results"></div>
        </div>
      </div>
    </div>
    <div class="ui vertical segment">
      <p></p>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      id: ''
    }
  },
  methods: {
    create () {
      window.location.href = '/cases/new'
    }
  }
}
</script>

<style scoped>
.masthead.segment {
  min-height: 350px;
  padding: 1em 0em;
}
.masthead .logo.item img {
  margin-right: 1em;
}
.masthead .ui.menu .ui.button {
  margin-left: 0.5em;
}
.masthead h1.ui.header {
  margin-top: 1.5em;
  margin-bottom: 0em;
  font-size: 2.9em;
  font-weight: normal;
}
.masthead h2 {
  font-size: 1.6em;
  font-weight: normal;
}
</style>
