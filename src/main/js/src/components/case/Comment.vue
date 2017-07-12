<template>
  <div class="comment">
    <a class="avatar">
      <i class="circular large user icon" :class="{orange: isAdmin}"/>
    </a>
    <div class="content">
      <span class="author">{{comment.author}}</span>
      <div class="metadata">
        <span class="date">{{comment.created | dateTime}}</span>
      </div>
      <div class="text">
        {{comment.text}}
      </div>
      <div class="actions">
        <a @click="$emit('del')">
          <i class="close icon"/>삭제
        </a>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['comment', 'isAdmin']
  }
</script>
