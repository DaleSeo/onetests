import superagent from 'superagent'
import config from '../../config'

const restUrl = config.BACKEND_URL + '/services'

exports.list = function () {
  return superagent.get(restUrl)
    .then(res => res.body._embedded.services)
}

exports.save = function (service) {
  console.log('#save:', service)
  if (service.id) {
    return this.modify(service)
  } else {
    return this.create(service)
  }
}

exports.create = function (service) {
  return superagent.post(restUrl)
    .send(service)
}

exports.modify = function (service) {
  console.log('modify:', service)
  return superagent.patch(restUrl + '/' + service.id)
    .send(service)
}

exports.detail = function detail(id) {
  return superagent.get(restUrl + '/' + id)
    .then(res => res.body)
}
