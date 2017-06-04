<template>
  <div style="height: 100%" class="ui container">
    <div style="height: 100%" class="ui middle aligned center aligned grid">
      <div class="six wide column">
        <h2 class="ui teal header">LOG IN</h2>
        <form method="POST" class="ui large form" @submit.prevent="login">
          <div class="ui stacked segment">
            <div class="field">
              <div class="ui left icon input"><i class="user icon"></i>
                <input type="email" v-model="email" placeholder="이메일"/>
              </div>
            </div>
            <div class="field">
              <div class="ui left icon input"><i class="lock icon"></i>
                <input type="password" v-model="password" placeholder="패스워드"/>
              </div>
            </div>
            <button type="submit" class="ui fluid large teal button">로그인</button>
          </div>
        </form>
        <div class="ui message">
          아직 계정이 없으세요?
          &nbsp;<a href="/signup"><b>회원가입</b></a>
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
  },
  methods: {
    login () {
      console.log(this.email + ':' + this.password)
      superagent.get('http://localhost:3000/login?username=user&password=pass')
        .then(res => {
          console.log(res)
          location.href = '/'
        })
    }
  }
}
</script>
