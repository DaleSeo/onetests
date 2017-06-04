<template>
  <div class="container">
    <Title icon="user" title="Profile" subTitle="프로필"/>
    <pre>{{user}}</pre>

  </div>
</template>

<script>
import superagent from 'superagent'

export default {
  data () {
    return {
      user: {
        email: '',
        name: '',
        roles: [],
        employeeNo: '',
        createdDate: new Date()
      }
    }
  },
  created () {
    superagent.get('/api/principal')
      .then(res => {
        console.log('#res:', res)
        return res
      })
      .then(res => res.body)
      .then(user => this.user = user)
      .catch(err => toastr.error)
  }
}
</script>
