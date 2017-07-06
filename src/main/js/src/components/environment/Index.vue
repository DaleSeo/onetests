<template>
  <div class="ui container">
    <Title icon="options" title="Environments" subTitle="환경 관리"/>
    <div class="ui grid">
      <div class="eight wide column">
        <ServiceFilter v-model="service"/>
        <div class="ui icon message" v-if="!service">
          <i class="warning circle icon"/>
          <div class="content">
            <div class="header">서비스를 선택해주세요.</div>
          </div>
        </div>
        <div class="ui icon message" v-if="service && environments.length === 0">
          <i class="info circle icon"/>
          <div class="content">
            <div class="header">등록된 환경이 없습니다.</div>
          </div>
        </div>
        <List :environments="environments" @pick="pick" v-if="environments" style="padding-top: 15px"/>
      </div>
      <div class="eight wide column">
        <div class="ui right floated teal icon labeled button" @click="add" v-show="!environment && service">
          <i class="plus icon"/>등록
        </div>
        <Edit :environment="environment" @save="save" v-if="environment"/>
      </div>
    </div>
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
    cancel () {
      this.environment = null
      this.fetch()
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
