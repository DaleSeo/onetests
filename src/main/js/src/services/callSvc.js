import superagent from 'superagent'
import config from '../config'

const restUrl = config.BACKEND_URL + '/calls'

function list(suiteId) {
  let url = restUrl
  if (suiteId) {
    url = restUrl + '/search/findBySuiteId?suiteId=' + suiteId
  }
  console.log(url)
  return superagent.get(url)
    .then(res => res.body._embedded.calls)
}

function save(call) {
  if (call.id) {
    return modify(call.id, call)
  } else {
    delete call.id
    return create(call)
  }
}

function create(call) {
  return superagent.post(restUrl)
    .send(call)
}

function modify(id, call) {
  return superagent.put(restUrl + '/' + id)
    .send(call)
}

function detail(id) {
  return superagent.get(restUrl + '/' + id)
    .then(res => res.body)
}

function remove(id) {
  return superagent.delete(restUrl + '/' + id)
}

export default {
  list, save, detail, remove
}
