import superagent from 'superagent'
import config from '../../config'
import utils from './utils'

const restUrl = config.BACKEND_URL + '/calls'

function list(suiteId) {
  let url = restUrl
  if (suiteId) {
    url += '/search/findBySuiteId?suiteId=' + suiteId
  } else {
    url += '?sort=id,desc'
  }
  console.log(url)
  return superagent.get(url)
    .then(res => res.body._embedded.calls)
}

function findCallWithArray(id) {
  return superagent.get(restUrl + '/' + id)
    .then(res => res.body)
    .then(call => {
      let req = Object.assign({}, call.request)
      req.queries = utils.objToArr(req.queries)
      req.headers = utils.objToArr(req.headers)
      call.req = req
      return call
    })
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
  list, save, detail, remove, findCallWithArray
}
