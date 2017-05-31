<template>
  <div class="ui mini form segment">
    <div class="fields" v-if="entries" v-for="(entry, index) in entries">
      <div class="five wide field">
        <input type="text" v-model="entry.key" :placeholder="'key ' + (index + 1)"/>
      </div>
      <div class="ten wide field">
        <input type="text" v-model="entry.value" :placeholder="'value ' + (index + 1)"/>
      </div>
      <div class="one wide field" style="text-align: center">
        <button type="button" class="ui circular mini compact negative icon button" @click="del(index)">
          <i class="minus icon"/>
        </button>
      </div>
    </div>
    <div class="fields">
      <div class="six wide field"/>
      <div class="nine wide field"/>
      <div class="one wide field" style="text-align: center">
        <button type="button" class="ui circular mini compact positive icon button" @click="add">
          <i class="plus icon"/>
        </button>
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
