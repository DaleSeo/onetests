<template>
  <h3 class="ui dividing header">
    <i :class="icon" class="icon"></i>
    <div class="content">
      {{title}}
      <div class="sub header">{{subTitle}}</div>
    </div>
  </h3>
</template>

<script>
export default {
  props: ["icon", "title", "subTitle"]
}
</script>
