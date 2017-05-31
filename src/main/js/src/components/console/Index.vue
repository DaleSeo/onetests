<template>
  <div class="ui container">
    <h3 class="ui dividing header">
      <i class="terminal icon"/>
      <div class="content">
        Console
        <div class="sub header">콘솔</div>
      </div>
    </h3>

    <div class="row">
      <div class="col-md-4">
        <ApiList
          @pickApi="pickApi"
        />
      </div>
      <div class="col-md-8">
        <Request
          :request="request"
          @callApi="callApi"
        />
        <Buttons
          @callApi="callApi"
          @cancel="cancel"
        />
        <Response
          :response="response"
          :inProgress="inProgress"
        />
      </div>
    </div>
    <Share :callId="callId"/>
    <Curl :request="request"/>
  </div>
</template>

<script>
import callApi from '../../services/callApi'
import serviceSvc from '../../services/serviceSvc'

import ApiList from './ApiList.vue'
import Request from './Request.vue'
import Buttons from './Buttons.vue'
import Response from './Response.vue'
import Share from './Share.vue'
import Curl from './Curl.vue'

export default {
  components: {ApiList, Request, Buttons, Response, Share, Curl},
  data () {
    return {
      request: this.initRequest(),
      response: this.initResponse(),
      apiId: '',
      callId: '5912a282ec46ff6c417a9481',
      inProgress: false,
      loading: false
    }
  },
  methods: {
    initRequest () {
      return {
        method: 'GET',
        queries: [],
        headers: [
          {key: 'Accept', value: 'application/json;charset=UTF-8'},
          {key: 'Content-type', value: 'application/json;charset=UTF-8'}
        ],
        url: 'http://jsonplaceholder.typicode.com/posts/1',
        path: '',
        body: ''
      }
    },
    initResponse () {
      return {
        statusCode: '',
        statusMessage: '',
        headers: {},
        body: '',
        error: ''
      }
    },
    callApi () {
      this.inProgress = true
      this.response = this.initResponse()
      callApi(this.request, this.apiId)
        .then(res => this.response = res)
        .catch(err => {
          console.log(err)
          this.response.error = err.response.body.message
        })
        .then(_ => this.inProgress = false)
    },
    cancel () {
      this.request = this.initRequest()
      this.response = this.initResponse()
      toastr.success('모든 입력 값들이 삭제되었습니다.')
    },
    pickApi (api) {
      console.log('Index.vue#pickApi()', api)
      this.apiId = api.id
      this.request.method = api.method
      this.request.url = api.url
      this.request.body = api.body
      this.response = this.initResponse
    }
  }
}
</script>
