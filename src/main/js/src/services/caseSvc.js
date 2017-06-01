import superagent from 'superagent'
import utils from './utils'
import config from '../../config'

const restUrl = config.BACKEND_URL + '/cases'

function list(serviceId) {
  let url = restUrl

  if (serviceId) {
    url += `/search/findByServiceId?serviceId=${serviceId}`
  } else {
    url += '?sort=id,desc'
  }

  console.log(url)
  return superagent.get(url)
    .then(res => res.body._embedded.cases)
}

function save(_cas) {
  let cas = Object.assign({}, _cas)
  cas.request.queries = utils.arrayToObj(cas.request.queries)
  cas.request.headers = utils.arrayToObj(cas.request.headers)

  console.log('#cas:', cas)

  if (cas.id) {
    return modify(cas.id, cas)
  } else {
    delete cas.id
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
  return superagent.get(restUrl + '/' + id + '?projection=inlineService')
    .then(res => res.body)
}

function remove(id) {
  return superagent.delete(restUrl + '/' + id)
}

export default {
  list, save, detail, remove
}
