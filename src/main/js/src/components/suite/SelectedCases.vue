<template>
  <div id="caseList">
    <h5 class="ui top attached inverted small header">
      선택한 테스트 케이스 ({{cases.length}})
    </h5>
    <CaseList :cases="cases"/>
  </div>
</template>

<script>
export default {
  props: ['cases']
}
</script>
