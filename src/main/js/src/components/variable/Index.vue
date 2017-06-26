<template>
  <div class="ui container">
    <Title icon="map signs" title="Variables" subTitle="변수 관리"/>
    <ServiceFilter :serviceId="serviceId" @change="onServiceIdChange" />
    <pre>{{variables}}</pre>
  </div>
</template>

<script>
import serviceSvc from '../../services/serviceSvc'
import List from './List.vue'

export default {
  components: {List},
  data () {
    return {
      serviceId: '',
      variables: []
    }
  },
  methods: {
    onServiceIdChange (serviceId) {
      console.log('#onServiceIdChange()')
      serviceSvc.detail(serviceId)
        .then(service => this.variables = service.variables)
        .catch(err => {
          console.error(err)
          throw err
        })
        .catch(err => toastr.error('목록 조회 실패'))
    },
    add () {
    },
    del () {
    }
  }
}
</script>
