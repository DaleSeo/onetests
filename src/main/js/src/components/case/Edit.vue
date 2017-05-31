<template>
  <div class="ui container">
    <Title icon="cube" title="Test Case" subTitle="개별 검증 등록"/>
    <form class="ui form segment" @submit.prevent>
      <div class="fields">
        <div class="four wide required field">
          <label>메소드</label>
          <select class="ui fluid search dropdown" v-model="cas.method" required>
            <option v-for="method in methods">{{method}}</option>
          </select>
        </div>
        <div class="twelve wide required field">
          <label>호스트를 제외한 URL</label>
          <input type="text" v-model="cas.path">
        </div>
      </div>

      <div class="field">
        <label>쿼리 파라미터</label>
        <Entries :entries="cas.queries"/>
      </div>

      <div class="field">
        <label>헤더</label>
        <Entries :entries="cas.headers"/>
      </div>

      <div class="field">
        <label>바디</label>
        <textarea v-model="cas.body"/>
      </div>

      <div class="field">
        <label>설명</label>
        <textarea v-model="cas.description"/>
      </div>

      <button type="submit" class="ui positive button" tabindex="0">저장</button>
      <button type="reset" class="ui negative button" tabindex="0">취소</button>
    </form>
  </div>
</template>

<script>
import Entries from './Entries.vue'

export default {
  components: { Entries },
  data () {
    return {
      cas: this.initCase(),
      methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD']
    }
  },
  methods: {
    initCase () {
      return {
        method: 'GET',
        queries: [],
        headers: [
          {key: 'Accept', value: 'application/json;charset=UTF-8'},
          {key: 'Content-type', value: 'application/json;charset=UTF-8'}
        ],
        path: '/posts/1',
        body: '',
        description: ''
      }
    }
  }
}
</script>
