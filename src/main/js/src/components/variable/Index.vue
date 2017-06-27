<template>
  <div class="ui container">
    <Title icon="map signs" title="Variables" subTitle="변수 관리"/>
    <ServiceFilter v-model="service"/>
    <div class="ui message" v-if="!service">
      <p>서비스를 선택해주세요</p>
    </div>
    <button class="ui button" @click="add" v-if="service">Add</button>
    <List :environments="environments" v-if="environments"/>
    <Edit :environment="environment" v-if="environment"/>
    <button class="ui button" @click="save" v-if="environment">Save</button>
  </div>
</template>

<script>
import List from './List.vue'
import Edit from './Edit.vue'

export default {
  components: {List, Edit},
  data () {
    return {
      service: null,
      environments: [],
      environment: null
    }
  },
  watch: {
    service (val) {
      if (val && val.id) {
        this.fetch()
      } else {
        this.environments = []
      }
    }
  },
  methods: {
    fetch () {
      this.$http.get('/api/environments/search/byService?id=' + this.service.id)
        .then(res => res.body._embedded.environments)
        .then(environments => this.environments = environments)
        .catch(err => toastr.error('환경 변수 조회 실패'))
    },
    save () {
      console.log('#save')
      let rest
      if (this.environment.id) {
        rest = this.$http.patch('/api/environments/' + this.environment.id, this.environment)
      } else {
        rest = this.$http.post('/api/environments', this.environment)
      }
      rest.then(_ => toastr.success('환경 변수가 저장되었습니다.'))
        .catch(err => toastr.error('환경 변수 저장 실패'))
        .then(_ => this.environment = null)
    },
    add () {
      this.environment = {
        code: '',
        title: '',
        description: '',
        variables: {},
        service: { id: this.service.id }
      }
    }
  }
}
</script>
