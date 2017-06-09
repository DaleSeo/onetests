<template>
  <div id="caseSearch" class="ui category search" :class="{fluid}">
    <div class="ui icon input" :class="size">
      <input class="prompt" type="text" placeholder="Search...">
      <i class="search icon"></i>
    </div>
    <div class="results"></div>
  </div>
</template>

<script>
const action = {
  url: "/cases/",
  text: "등록된 모든 테스트 케이스 보기"
}

export default {
  props: ['fluid', 'size'],
  mounted () {
    console.log('#caseSearch#updated')
    $('#caseSearch.ui.search').search({
      type: 'category',
      minCharacters: 3,
      apiSettings: {
        url: '/api/search?q={query}',
        onResponse (results) {
          return { results, action }
        }
      }
    })
  }
}
</script>
