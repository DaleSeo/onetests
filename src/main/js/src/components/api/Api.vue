<template>
  <div class="row">
    <div class="col-md-7">
      <ApiList
        :apis="apis" :activeApi="api"
        @detail="detail" @create="create"
      />
    </div>
    <div class="col-md-5">
      <ApiForm v-show="showForm"
        :api="api"
        @reset="reset" @save="save" @remove="remove"
      />
    </div>
  </div>
</template>

<script>
import _ from 'lodash'

import ApiList from './ApiList.vue'
import ApiForm from './ApiForm.vue'

import apiSvc from '../../services/apiSvc'

export default {
  components: {ApiList, ApiForm},
  props: ['id'],
  data () {
    return {
      showForm: false,
      apis: [],
      api: this.initApi()
    }
  },
  created () {
    this.list()
  },
  methods: {
    initApi () {
      return {
        id: '',
        title: '',
        method: 'GET',
        path: '',
        body: '',
        description: '',
        exclusions: [],
        serviceId: this.id,
        skip: false
      }
    },
    list () {
      console.log('Api.vue#list')
      apiSvc.list(this.id)
        .then(apis => this.apis = apis)
        .catch(err => {
          console.error(err)
          toastr.error('목록 조회 실패')
        })
    },
    reset () {
      console.log('Api.vue#reset')
      this.showForm = false
      this.api = this.initApi()
    },
    detail (api) {
      console.log('Api.vue#detail')
      this.api = api
      this.showForm = true
    },
    create () {
      console.log('Api.vue#create')
      this.reset()
      this.showForm = true
    },
    save () {
      console.log('Api.vue#save')
      apiSvc.save(_.cloneDeep(this.api))
        .then(_ => toastr.success('저장되었습니다'))
        .catch(err => toastr.error('저장 실패'))
        .then(this.reset)
        .then(this.list)
    },
    remove () {
      console.log('Api.vue##remove')
      apiSvc.remove(this.api.id)
        .then(_ => toastr.success('삭제되었습니다'))
        .catch(err => toastr.error('삭제 실패'))
        .then(this.reset)
        .then(this.list)
    }
  }
}
</script>
