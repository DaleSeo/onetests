import superagent from 'superagent'
import utils from './utils'
import config from '../../config'

const restUrl = config.BACKEND_URL + '/cases'

function list(serviceId) {
  let url = restUrl

  if (serviceId) {
    url += `/search/findByServiceId?serviceId=${serviceId}`
  } else {
    url += '?size=1000'
  }

  console.log(url)
  return superagent.get(url)
    .then(res => res.body._embedded.cases)
}

function save(_cas) {
  let cas = Object.assign({}, _cas)

  console.log('#cas:', cas)

  cas.request.queries = utils.arrToObj(cas.request.queries)
  cas.request.headers = utils.arrToObj(cas.request.headers)
  cas.response.headers = utils.arrToObj(cas.response.headers)

  if (cas.id) {
    return modify(cas.id, cas)
      .then(res => {
        console.log('###res.body:', res.body)
        return cas.id
      })
  } else {
    return create(cas)
      .then(res => res.body.id)
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
  return superagent.get(restUrl + '/' + id + '?projection=inline')
    .then(res => res.body)
}

function findOneWithArray(id) {
  return superagent.get(restUrl + '/' + id + '?projection=inline')
    .then(res => res.body)
    .then(cas => {
      let req = Object.assign({}, cas.request)
      let res = Object.assign({}, cas.response)
      req.queries = utils.objToArr(req.queries)
      req.headers = utils.objToArr(req.headers)
      res.headers = utils.objToArr(res.headers)
      cas.request = req
      cas.response = res
      if (!cas.response) {
        cas.response = {
          statusCode: 200,
          headers: [],
          body: ''
        }
      }
      return cas
    })
}

function remove(id) {
  return superagent.delete(restUrl + '/' + id)
}

export default {
  list, save, detail, remove, findOneWithArray
}
