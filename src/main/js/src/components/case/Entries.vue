<template>
  <div>
    <div class="row" v-if="entries" v-for="(entry, index) in entries">
      <div class="col-sm-3">
        <input type="text" class="form-control input-sm" v-model="entry.key"/>
      </div>
      <div class="col-sm-8">
        <input type="text" class="form-control input-sm" v-model="entry.value"/>
      </div>
      <div class="col-sm-1">
        <button type="button" class="close" @click="del(index)">&times;</button>
      </div>
    </div>
    <div class="row">
      <div class="col-sm-12">
        <button type="button" class="btn btn-default btn-sm" @click="add"><i class="fa fa-plus"/></button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['entries'],
  methods: {
    add () {
      this.entries.push({key: '', value: ''})
    },
    del (index) {
      this.entries.splice(index, 1)
    }
  }
}
</script>

<style scoped>
.row {
  margin: 5px 0;
}
.col-sm-3 {
  padding-right: 5px;
}
.col-sm-8 {
  padding-left: 5px;
  padding-right: 0;
}
.col-sm-1 {
  padding-left: 0;
}
</style>
