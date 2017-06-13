<template>
  <div class="ui mini form segment">
    <div class="fields" v-if="pairs" v-for="(pair, index) in pairs">
      <div class="five wide field">
        <input type="text" v-model="pair.key" :placeholder="'key ' + (index + 1)" :disabled="readonly" @input="updateValue"/>
      </div>
      <div class="ten wide field">
        <input type="text" v-model="pair.value" :placeholder="'value ' + (index + 1)" :disabled="readonly" @input="updateValue"/>
      </div>
      <div class="one wide field" style="text-align: center">
        <button type="button" class="ui circular mini compact negative icon button" :class="{disabled: readonly}" @click="del(index)">
          <i class="minus icon"/>
        </button>
      </div>
    </div>
    <div class="fields">
      <div class="six wide field"/>
      <div class="nine wide field"/>
      <div class="one wide field" style="text-align: center">
        <button type="button" class="ui circular mini compact positive icon button" :class="{disabled: readonly}" @click="add">
          <i class="plus icon"/>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import utils from '../../services/utils'

export default {
  props: {
    value: Object,
    readonly: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      pairs: []
    }
  },
  watch: {
    value (val) {
      this.pairs = utils.objToArr(val)
    }
  },
  methods: {
    add () {
      this.pairs.push({key: '', value: ''})
      this.updateValue ()
    },
    del (index) {
      this.pairs.splice(index, 1)
      this.updateValue ()
    },
    updateValue () {
      this.$emit('input', utils.arrToObj(this.pairs))
    }

  }
}
</script>
