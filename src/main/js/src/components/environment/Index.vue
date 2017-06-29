<template>
  <div class="ui container">
    <Title icon="options" title="Environments" subTitle="환경 변수"/>
    <ServiceFilter v-model="service"/>
    <div class="ui right floated teal icon labeled button" @click="add" v-if="service">
      <i class="plus icon"/>등록
    </div>
    <div>&nbsp;</div>
    <div class="ui message" v-if="!service">
      <p>서비스를 선택해주세요</p>
    </div>
    <div class="ui message" v-if="service && environments.length === 0">
      <p>등록된 환경 변수가 없습니다.</p>
    </div>
    <List :environments="environments" @pick="pick" v-if="environments"/>
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
        .then(_ => {
          this.fetch()
          this.environment = null
        })
    },
    add () {
      this.environment = {
        code: '',
        title: '',
        description: '',
        variables: {},
        serviceId: this.service.id
      }
    },
    pick (environment) {
      console.log('#pick:', environment)
      this.environment = environment
    }
  }
}
</script>
