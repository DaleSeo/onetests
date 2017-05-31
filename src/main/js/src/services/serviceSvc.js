import superagent from 'superagent'
import config from '../config'

const restUrl = config.BACKEND_URL + '/services'

function list() {
  return superagent.get(restUrl)
    .then(res => res.body._embedded.services)
}

function create(service) {
  return superagent.post(restUrl)
    .send(service)
}

function detail(id) {
  return superagent.get(restUrl + '/' + id)
    .then(res => res.body)
}

export default {
  list, create, detail
}
