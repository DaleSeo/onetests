<template>
  <div id="methodDropdown" class="ui selection dropdown" :class="{disabled: readonly}">
    <input type="hidden" :value="value" @change="change">
    <i class="dropdown icon"></i>
    <span class="default text">메소드</span>
    <div class="menu">
      <div class="item" :data-value="method" v-for="method in methods">{{method}}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    value: String,
    readonly: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD']
    }
  },
  watch: {
    value (val) {
      $('#methodDropdown.ui.dropdown').dropdown('set selected', val)
    }
  },
  mounted () {
    $('#methodDropdown.ui.dropdown').dropdown()
  },
  methods: {
    change (event) {
      this.$emit('input', event.target.value)
    }
  }
}
</script>
