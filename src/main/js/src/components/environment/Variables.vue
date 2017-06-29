<template>
  <div>
    <div class="fields" v-for="(value, key) in variables">
      <div class="five wide field">
        <input :value="key" @change="changeKey(key, $event.target.value)"/>
      </div>
      <div class="ten wide field">
        <input :value="value" @change="changeValue(key, $event.target.value)"/>
      </div>
      <div class="one wide field">
        <div class="ui tiny icon button" @click="del(key)">
          <i class="red minus icon"/>
        </div>
      </div>
    </div>
    <div class="fields">
      <div class="five wide field">
        <input placeholder="key" v-model="variable.key">
      </div>
      <div class="ten wide field">
        <input placeholder="value" v-model="variable.value">
      </div>
      <div class="one wide field">
        <div class="ui tiny icon button" @click="add">
          <i class="green plus icon"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['variables'],
  data () {
    return {
      variable: {key: '', value: ''}
    }
  },
  methods: {
    add () {
      console.log('#add:', this.variable)
      if (this.variables[this.variable.key]) {
        alert('duplicated key!')
        return
      }
      this.$set(this.variables, this.variable.key, this.variable.value)
      this.variable = {key: '', value: ''}
    },
    del (key) {
      console.log('#del:', key)
      this.$delete(this.variables, key)
    },
    changeKey (key, newKey) {
      console.log('#changeKey:', key, newKey)
      this.$set(this.variables, newKey, this.variables[key])
      this.$delete(this.variables, key)
    },
    changeValue (key, newValue) {
      console.log('#changeValue:', key, newValue)
      this.$set(this.variables, key, newValue)
    }
  }
}
</script>
