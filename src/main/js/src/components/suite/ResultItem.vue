<template>
  <div class="item">
    <div class="content">
      <div class="header">
        {{result.createdDate | formatDate}}
      </div>
      <div class="meta">
        <i class="green thumbs up icon"></i> {{result.success}}
        &nbsp;&nbsp;&nbsp;
        <i class="red thumbs down icon"></i> {{result.failure}}
      </div>
      <div class="description">
        <!-- {{result}} -->
      </div>
      <div class="extra">
        <button class="ui right floated primary mini button" @click="view">
          상세 보기
          <i class="right chevron icon"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['result'],
  methods: {
    view () {
      this.$emit('view', this.result.id)
    }
  }
}
</script>
