<template>
  <div class="container">
    <Title icon="user" title="Profile" subTitle="프로필"/>
    <table class="ui definition table">
      <tbody>
        <tr>
          <td>ID</td>
          <td>{{user.id}}</td>
        </tr>
        <tr>
          <td>이름</td>
          <td>{{user.name}}</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>{{user.email}}</td>
        </tr>
        <tr>
          <td>사번</td>
          <td>{{user.employeeNo}}</td>
        </tr>
        <tr>
          <td>가입일</td>
          <td>{{user.createdDate}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      user: {
        id: '',
        email: '',
        name: '',
        roles: [],
        employeeNo: '',
        createdDate: new Date()
      }
    }
  },
  created () {
    this.$http.get('/api/auth/principal')
      .then(res => res.body)
      .then(user => this.user = user)
      .catch(err => toastr.error)
  }
}
</script>
