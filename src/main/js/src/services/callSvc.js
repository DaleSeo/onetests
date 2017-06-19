import superagent from 'superagent'
import config from '../../config'
import utils from './utils'

const restUrl = config.BACKEND_URL + '/calls'

exports.list = function ({caseId, suiteId}) {
  let url = restUrl
  if (caseId) {
    url += '/search/byCaseId?caseId=' + caseId
  } else if (suiteId) {
    url += '/search/bySuiteId?suiteId=' + suiteId
  } else {
    url += '?size=100&sort=id,desc'
  }

  console.log(url)
  return superagent.get(url)
    .then(res => res.body._embedded.calls)
}

exports.findOneWithArray = function (id) {
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

exports.save = function (call) {
  if (call.id) {
    return modify(call.id, call)
  } else {
    delete call.id
    return create(call)
  }
}

exports.create = function (call) {
  return superagent.post(restUrl)
    .send(call)
}

exports.modify = function (id, call) {
  return superagent.put(restUrl + '/' + id)
    .send(call)
}

exports.detail = function (id) {
  return superagent.get(restUrl + '/' + id + '?projection=inline')
    .then(res => res.body)
}

exports.remove = function (id) {
  return superagent.delete(restUrl + '/' + id)
}
