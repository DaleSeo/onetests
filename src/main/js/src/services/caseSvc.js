import superagent from 'superagent'
import config from '../config'

const restUrl = config.BACKEND_URL + '/cases'

function list(suiteId) {
  let url = restUrl
  if (suiteId) {
    url = restUrl + '/search/findBySuiteId?suiteId=' + suiteId
  }
  console.log(url)
  return superagent.get(url)
    .then(res => res.body._embedded.cases)
}

function save(cas) {
  if (cas.id) {
    return modify(cas.id, cas)
  } else {
    delete cas.id
    return create(cas)
  }
}

function create(cas) {
  return superagent.post(restUrl)
    .send(cas)
}

function modify(id, cas) {
  return superagent.put(restUrl + '/' + id)
    .send(cas)
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
