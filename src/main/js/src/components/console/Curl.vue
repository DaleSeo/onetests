<template>
  <div class="modal fade" id="curl" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Curl 변환</h4>
        </div>
        <div class="modal-body">
          <textarea rows=10 class="form-control" :value="curl"/>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary"
            v-clipboard:copy="curl"
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
