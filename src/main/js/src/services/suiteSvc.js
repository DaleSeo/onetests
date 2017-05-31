import superagent from 'superagent'
import config from '../config'

const restUrl = config.BACKEND_URL + '/suites'

exports.list = function () {
  let url = restUrl
  return superagent.get(url)
    .then(res => res.body._embedded.suites)
}

exports.create = function (suite, selectedApis) {
  let requests = selectedApis.map(api => {
    return {
      method: api.method,
      url: api.url,
      body: api.body
    }
  })
  suite.requests = requests
  return superagent.post(restUrl)
    .send(suite)
    .then(res => res.body.id)
}

exports.detail = function (id) {
  return superagent.get(restUrl + '/' + id)
    .then(res => res.body)
}

exports.remove = function (id) {
  return superagent.delete(restUrl + '/' + id)
}

exports.run = function (id) {
  return superagent.post(restUrl + '/run?id=' + id)
}
