<template>
  <div class="item">
    <div class="content">
      <div class="header">
        {{title}}
      </div>
      <div class="description">
        <i class="blue rocket icon"></i> {{result.total}}
        &nbsp;&nbsp;
        <i class="green thumbs up icon"></i> {{result.success}}
        &nbsp;&nbsp;
        <i class="red thumbs down icon"></i> {{result.failure}}
      </div>
      <div class="meta">
        <i class="calendar icon"/>{{result.createdDate | formatDate}}
        <i class="wait icon"/>{{result.elapsedTime === 0 ? 'N/A' : result.elapsedTime}} ms
      </div>
      <div class="extra">
        <button class="ui right floated mini button" @click="view">
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
  computed: {
    title () {
      return this.result.failure === 0 ? '검증 성공' : '검증 실패'
    }
  },
  methods: {
    view () {
      this.$emit('view', this.result.id)
    }
  }
}
</script>
