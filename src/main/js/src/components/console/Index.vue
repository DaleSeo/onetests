<template>
  <div class="ui container">
    <h3 class="ui dividing header">
      <i class="terminal icon"/>
      <div class="content">
        Console
        <div class="sub header">콘솔</div>
      </div>
    </h3>

    <div class="ui grid">
      <div class="five wide column">
        <EnvironmentDropdown v-model="environment" :fluid="true" look="filter"/>
        <div>&nbsp;</div>
        <CaseList
          @pickApi="pickApi"
        />
      </div>
      <div class="eleven wide column">
        <Request
          :request="request"
          :serviceId="serviceId"
          @callApi="callApi"
        />
        <br/>
        <Response
          :response="response"
          :callId="callId"
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
import callSvc from '../../services/callSvc'

import EnvironmentDropdown from '../common/EnvironmentDropdown.vue'
import CaseList from './CaseList.vue'
import Request from './Request.vue'
import Buttons from './Buttons.vue'
import Response from './Response.vue'
import Share from './Share.vue'
import Curl from './Curl.vue'

export default {
  components: {EnvironmentDropdown, CaseList, Request, Buttons, Response, Share, Curl},
  data () {
    return {
      request: this.initRequest(),
      response: this.initResponse(),
      environment: null,
      serviceId: '',
      callId: this.$route.query.callId || '',
      inProgress: false,
      loading: false
    }
  },
  created () {
    this.fetchCall ()
  },
  methods: {
    initRequest () {
      return {
        method: 'GET',
        queries: {},
        headers: {},
        host: '',
        path: '',
        body: '',
        hostId: ''
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
    fetchCall () {
      if (!this.callId) {
        return
      }
      callSvc.findOneWithArray(this.callId)
        .then(call => this.request = call.req)
        .catch(toastr.error)
    },
    callApi () {
      this.inProgress = true
      this.response = this.initResponse()
      callApi(this.request, this.environment)
        .then(call => {
          this.callId = call.id
          this.response = call.response
        })
        .catch(err => {
          console.log(err)
          this.response.error = err.response.text
        })
        .then(_ => this.inProgress = false)
    },
    cancel () {
      this.request = this.initRequest()
      this.response = this.initResponse()
      toastr.success('모든 입력 값들이 삭제되었습니다.')
    },
    pickApi (cas) {
      console.log('Index.vue#pickApi()', cas)
      this.serviceId = cas.serviceId
      this.request.hostId = cas.hostId
      this.request.method = cas.request.method
      this.request.host = cas.request.host
      this.request.path = cas.request.path
      this.request.queries = cas.request.queries
      this.request.headers = cas.request.headers
      this.request.body = cas.request.body
      this.response = this.initResponse
    }
  }
}
</script>
