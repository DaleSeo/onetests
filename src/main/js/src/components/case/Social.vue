<template>
  <div>
    <div class="ui comments">
      <div class="comment" v-for="comment in comments">
        <a class="avatar">
          <i class="big user icon"/>
        </a>
        <div class="content">
          <span class="author">{{comment.author}}</span>
          <div class="metadata">
            <span class="date">{{comment.date}}</span>
          </div>
          <div class="text">
            {{comment.text}}
          </div>
        </div>
      </div>
    </div>
    <form class="ui reply form">
      <div class="field">
        <textarea></textarea>
      </div>
      <div class="ui primary submit labeled icon button">
        <i class="icon edit"></i> Add Comment
        </div>
    </form>
  </div>
</template>

<script>
export default {
  props: ['cas'],
  data () {
    return {
      comments: []
    }
  },
  created () {
    this.fetch()
  },
  methods: {
    fetch () {
      this.comments = [
        {
          author: '서대영',
          date: '2017/07/02 21:00:43',
          text: '임의로 작성한 테스트입니다. 1'
        },
        {
          author: '최은봉',
          date: '2017/07/01 12:00:05',
          text: '임의로 작성한 테스트입니다. 2'
        }
      ]
    }
  }
}
</script>
