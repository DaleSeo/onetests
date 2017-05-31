<template>
  <div class="well">
    <form @submit.prevent="add" @reset="cancel">
      <div class="form-group">
        <label for="code">코드</label>
        <input id="code" type="text" class="form-control" required v-model="service.code"/>
      </div>
      <div class="form-group">
        <label for="name">이름</label>
        <input id="name" type="text" class="form-control" required v-model="service.name"/>
      </div>
      <div class="form-group">
        <label for="description">설명</label>
        <input id="description" type="text" class="form-control" required v-model="service.description"/>
      </div>
      <div class="form-group text-right">
        <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-floppy-o"/> 추가</button>
        <button type="reset" class="btn btn-sm btn-warning"><i class="fa fa-undo"/> 취소</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  props: ["service"],
  methods: {
    add () {
      this.$emit('add')
    },
    cancel () {
      this.$emit('cancel')
    }
  }
}
</script>
