<template>
  <div>
    <div class="ui comments">
      <Comment :comment="defaultComment" :isAdmin="true"/>
      <Comment :key="comment.id" :comment="comment" @del="del(comment.id)" v-for="comment in comments"/>
    </div>
    <form class="ui reply form" @submit.prevent="add">
      <div class="field">
        <textarea v-model="comment.text"/>
      </div>
      <button type="submit" class="ui right floated teal small labeled icon button">
        <i class="icon edit"/> 의견 추가
      </button>
    </form>
  </div>
</template>

<script>
import Comment from './Comment.vue'

export default {
  props: ['cas'],
  components: {Comment},
  data () {
    return {
      comments: [],
      comment: {
        caseId: this.cas.id,
        text: ''
      },
      defaultComment: {
        author: '관리자',
        created: this.cas.created,
        text: '테스트 케이스에 관련된 의견을 공유하는 공간입니다.'
      }
    }
  },
  created () {
    this.fetch()
  },
  methods: {
    fetch () {
      this.$http.get(`/api/comments?caseId=${this.cas.id}`)
        .then(res => res.body)
        .then(comments => this.comments = comments)
    },
    add () {
      this.$http.post('/api/comments', this.comment)
        .then(_ => toastr.success('의견이 추가되었습니다.'))
        .catch(err => toastr.error('의견 추가 실패'))
        .then(this.fetch)
        .then(_ => this.comment.text = '')
    },
    del (id) {
      this.$http.delete(`/api/comments/${id}`)
        .then(_ => toastr.success('의견이 삭제되었습니다.'))
        .catch(err => toastr.error('의견 삭제 실패'))
        .then(this.fetch)
    }
  }
}
</script>
