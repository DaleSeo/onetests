<template>
  <div id="modalShare" class="ui small basic modal">
    <div class="ui icon header">
      <i class="share alternate icon"></i>
      테스트케이스 공유
    </div>
    <div class="content">
      <div class="ui fluid input">
        <input type="url" :value="url"/>
      </div>
    </div>
    <div class="actions">
      <div class="ui green inverted ok button"
        v-clipboard:copy="url"
        v-clipboard:success="onCopy"
        v-clipboard:error="onError"
      >
        <i class="copy icon"/>
        Copy
      </div>
      <div class="ui red basic inverted cancel button">
        <i class="remove icon"></i>
        Close
      </div>
    </div>
  </div>
</template>

<script>
import buildCurl from '../../services/buildCurl'

export default {
  props: ["callId"],
  computed: {
    url () {
      return window.location.href + '?callId=' + this.callId
    }
  },
  methods: {
    onCopy (e) {
      toastr.success('클립보드로 복사되었습니다')
    },
    onError (e) {
      toastr.error('복사 실패')
    }
  }
}
</script>
