<template>
  <div style="height: 100%" class="ui container">
    <div style="height: 100%" class="ui middle aligned center aligned grid">
      <div class="six wide column">
        <h2 class="ui teal header">LOG IN</h2>
        <form method="POST" class="ui large form">
          <div class="ui stacked segment">
            <div class="field">
              <div class="ui left icon input"><i class="mail icon"></i>
                <input type="text" name="username" v-model="email" placeholder="이메일"/>
              </div>
            </div>
            <div class="field">
              <div class="ui left icon input"><i class="lock icon"></i>
                <input type="password" name="password" v-model="password" placeholder="패스워드"/>
              </div>
            </div>
            <button type="submit" class="ui fluid large teal button">로그인</button>
          </div>
        </form>
        <div class="ui message">
          아직 계정이 없으세요?
          &nbsp;<a href="/auth/signup"><b>회원가입</b></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import superagent from 'superagent'

export default {
  data () {
    return {
      email: '',
      password: ''
    }
  }
}
</script>
