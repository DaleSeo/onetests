import superagent from 'superagent'
import config from '../../config'

const restUrl = config.BACKEND_URL + '/hosts'

exports.list = function (serviceId) {
  let url = restUrl
  if (serviceId) {
    url = restUrl + '/search/findByServiceId?projection=inline&serviceId=' + serviceId
  } else {
    url = restUrl + '?projection=inline' 
  }
  return superagent.get(url)
    .then(res => res.body._embedded.hosts)
}

exports.save = function (host) {
  if (host.id) {
    return this.modify(host)
  } else {
    return this.create(host)
  }
}

exports.create = function (host) {
  return superagent.post(restUrl)
    .send(host)
}

exports.modify = function (host) {
  return superagent.patch(restUrl + '/' + host.id)
    .send(host)
}

exports.detail = function (id) {
  return superagent.get(restUrl + '/' + id)
    .then(res => res.body)
}

exports.remove = function (id) {
  return superagent.delete(restUrl + '/' + id)
}
