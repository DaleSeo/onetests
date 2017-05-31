import superagent from 'superagent'
import config from '../config'

const restUrl = config.BACKEND_URL + '/apis'

function list(serviceId) {
  let url = restUrl
  if (serviceId) {
    url = restUrl + '/search/findByServiceId?serviceId=' + serviceId
  }
  return superagent.get(url)
    .then(res => res.body._embedded.apis)
}

function save(api) {
  if (api.id) {
    return modify(api.id, api)
  } else {
    delete api.id
    return create(api)
  }
}

function create(api) {
  return superagent.post(restUrl)
    .send(api)
}

function modify(id, api) {
  return superagent.put(restUrl + '/' + id)
    .send(api)
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
