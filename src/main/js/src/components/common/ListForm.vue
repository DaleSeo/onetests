<template>
  <div>
    <div class="ui top attached message">
      <i class="info icon"/>{{title}}
      <small v-if="items">({{items.length}})</small>
    </div>
    <div class="ui bottom attached segment">
      <ul class="ui list">
        <li class="item" v-for="item in items">
          <span>{{item}}</span>
          <button type="button" class="close" @click="del(item)">&times;</button>
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
  props: ['variant', 'title', 'items'],
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
