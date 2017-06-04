<template>
  <div style="height: 100%" class="ui container">
    <div style="height: 100%" class="ui middle aligned center aligned grid">
      <div class="six wide column">
        <h2 class="ui teal header">회원 가입</h2>
        <div class="ui negative message" v-if="error">
          {{error}}
        </div>
        <form class="ui large form" @submit.prevent="signup">
          <div class="ui stacked segment">
            <div class="required field">
              <div class="ui left icon input"><i class="user icon"></i>
                <input type="text" v-model="user.name" placeholder="이름"/>
              </div>
            </div>
            <div class="required field">
              <div class="ui left icon input"><i class="mail icon"></i>
                <input type="email" v-model="user.email" placeholder="이메일"/>
              </div>
            </div>
            <div class="required field">
              <div class="ui left icon input"><i class="lock icon"></i>
                <input type="password" v-model="user.password" placeholder="패스워드"/>
              </div>
            </div>
            <div class="required field">
              <div class="ui left icon input"><i class="checkmark icon"></i>
                <input type="password" v-model="confirmPassword" placeholder="패스워드 확인"/>
              </div>
            </div>
            <button type="submit" class="ui fluid large teal button">제출</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import superagent from 'superagent'

export default {
  data () {
    return {
      user: {
        name: '',
        email: '',
        password: ''
      },
      confirmPassword: '',
      error: ''
    }
  },
  methods: {
    signup () {
      this.error = ''
      if (!(this.user.email && this.user.password && this.confirmPassword)) {
        return this.error = '모든 필드를 입력해주세요.'
      }
      if (this.user.password != this.confirmPassword) {
        return this.error = '패스워드가 일치하지 않습니다.'
      }
      this.$http.get('/api/checkDup?email=' + this.user.email)
        .then(res => {
          if (res.body) {
            return this.error = '이미 가입된 이메일입니다.'
          }
          this.$http.post('/api/signup', this.user)
            .then(_ => {
              location.href = '/'
            })
        })
        .catch(toastr.error)
    }
  }
}
</script>
