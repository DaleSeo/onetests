<template>
  <span>
    <div class="ui mini red label">{{method}}</div>
    <em>{{path}}</em>
  </span>
</template>

<script>
export default {
  props: ["method", "path"]
}
</script>
