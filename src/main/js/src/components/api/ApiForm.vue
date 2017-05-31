<template>
  <form @submit.prevent="save" @reset.prevent="reset">
    <div class="panel panel-default">
      <div class="panel-heading">
        <strong>규격 상세</strong>
        <small>({{api.id}})</small>
      </div><!-- panel-heading -->
      <div class="panel-body">
        <div class="form-group">
          <label for="title">이름</label>
          <input id="title" type="text" class="form-control" v-model="api.title"/>
        </div>
        <div class="form-group">
          <label for="method">메소드</label>
          <select id="method" class="form-control" v-model="api.method">
            <option>GET</option>
            <option>POST</option>
            <option>PUT</option>
            <option>DELETE</option>
          </select>
        </div>
        <div class="form-group">
          <label for="path">패스</label>
          <input id="path" type="text" class="form-control" v-model.lazy="api.path"/>
        </div>
        <div class="form-group">
          <label for="body">바디</label>
          <textarea id="body" class="form-control" placeholder="JSON 포멧" rows="3" v-model.lazy="api.body"/>
        </div>
        <div class="form-group">
          <label for="description">설명</label>
          <input id="description" class="form-control" placeholder="" v-model.lazy="api.description"/>
        </div>
        <div class="form-group">
          <label for="exclusion">비교 예외 항목</label>
          <input id="exclusion" class="form-control" placeholder="쉼표로 구분" v-model.lazy="exclusionsText"/>
        </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" v-model="api.skip">검증 생략
          </label>
        </div>
      </div><!-- panel-body -->
      <div class="panel-footer text-right">
        <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"/> 저장</button>
        <button type="reset" class="btn btn-sm btn-warning"><i class="fa fa-undo"/> 취소</button>
        <button type="button" class="btn btn-sm btn-danger" @click="remove"><i class="fa fa-trash-o"/> 삭제</button>
      </div><!-- panel-footer -->
    </div><!-- panel -->
  </form>
</template>

<script>
export default {
  props: ['api'],
  computed: {
    exclusionsText: {
      get: function () {
        return this.api.exclusions ? this.api.exclusions.join() : ''
      },
      set: function (text) {
        this.api.exclusions = text.split(',')
      }
    }
  },
  methods: {
    save () {
      this.$emit('save')
    },
    remove () {
      this.$emit('remove')
    },
    reset () {
      this.$emit('reset')
    }
  }
}
</script>
