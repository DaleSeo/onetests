<template>
  <div>
    <div class="ui top attached message">
      <div class="header">
        {{title}} <small v-if="items">({{items.length}})</small>
      </div>
    </div>
    <div class="ui bottom attached segment" :class="{loading}">
      <ul class="ui animated list">
        <li class="item" v-for="item in items">
          <i class="close icon" @click="del(item)" style="float: right"/>
          <span>{{item}}</span>
        </li>
      </ul>
    </div>
    <div class="ui action fluid input" :disabled="disabled">
      <input v-model="newItem" @keyup.enter="add"/>
      <button class="ui button" @click="add">추가</button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['variant', 'title', 'items', 'loading'],
  computed: {
    disabled () {
      return this.newItem.trim().length === 0
    }
  },
  data () {
    return {
      newItem: ''
    }
  },
  methods: {
    add () {
      if (this.disabled) return
      this.$emit('add', this.newItem)
      this.newItem = ''
    },
    del (item) {
      this.$emit('del', item)
    }
  }
}
</script>

<style scoped>
li {
  height: 2em;
}
</style>
