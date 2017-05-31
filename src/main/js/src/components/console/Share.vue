<template>
  <div class="modal fade" id="share" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">테스트케이스 공유</h4>
        </div>
        <div class="modal-body">
          <input type="url" class="form-control" :value="url"/>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary"
            v-clipboard:copy="url"
            v-clipboard:success="onCopy"
            v-clipboard:error="onError"
          >
            <i class="fa fa-clone"/> Copy
          </button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
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
