<template>
  <div class="ui items">
    <div class="item" v-for="result in results">
      <div class="ui tiny image">
        <h5 class="ui center aligned icon header">
          <i class="circular thumbs up icon"></i>PASS
        </h5>
      </div>
      <div class="middle aligned content">
        <div class="meta">
          <i class="calendar icon"/>
          {{result.createdDate | formatDate}}
        </div>
        <div class="description">
          <div class="ui left labeled button">
            <div class="ui basic green right pointing label">
              상태
            </div>
            <div class="ui green button">
              <i class="thumbs up icon"/>GOOD
            </div>
          </div>

          <div class="ui left labeled button">
            <div class="ui basic red right pointing label">
              헤더
            </div>
            <div class="ui red button">
              <i class="thumbs down icon"/>BAD!
            </div>
          </div>

          <div class="ui left labeled button">
            <div class="ui basic green right pointing label">
              바디
            </div>
            <div class="ui green button">
              <i class="thumbs down icon"/>GOOD
            </div>
          </div>
          <!-- {{result}} -->
        </div>
          <!-- <ResultLabel :result="result"/> -->
          <div class="extra">
            <a class="ui right floated mini button" :href="/calls/ + result.callId" :title="result.callId">
              <i class="browser icon"/>호출 상세
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['results']
}
</script>
