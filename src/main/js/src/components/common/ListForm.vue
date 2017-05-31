<template>
  <div class="panel" :class="panelClass">
    <div class="panel-heading">
      <strong>{{title}}</strong>
      <small v-if="items">({{items.length}})</small>
    </div>
    <ul class="list-group">
      <li class="list-group-item" v-for="item in items">
        <span>{{item}}</span>
        <button type="button" class="close" @click="del(item)">&times;</button>
      </li>
    </ul>
    <div class="panel-footer">
      <div class="input-group">
        <input type="text" class="form-control input-sm" v-model="newItem" @keyup.enter="add"/>
        <span class="input-group-btn">
          <button class="btn btn-primary btn-sm" type="button" :disabled="disabled" @click="add">
            <i class="fa fa-pencil-square-o"/> 추가
          </button>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['variant', 'title', 'items'],
  computed: {
    panelClass () {
      if (!this.variant) {
        this.variant = 'default'
      }
      return 'panel-' + this.variant
    },
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
