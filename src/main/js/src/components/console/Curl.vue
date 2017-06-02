<template>
  <div id="modalCurl" class="ui small basic modal">
    <div class="ui icon header">
      <i class="share alternate icon"></i>
      CURL 명령어로 변환
    </div>
    <div class="content">
      <div class="ui form">
        <div class="field">
          <textarea :value="curl"/>
        </div>
      </div>
    </div>
    <div class="actions">
      <div class="ui green inverted ok button"
        v-clipboard:copy="curl"
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
  props: ["request"],
  computed: {
    curl () {
      return buildCurl(this.request)
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
