import superagent from 'superagent'
import utils from './utils'
import config from '../../config'

const restUrl = config.BACKEND_URL + '/cases'

exports.list = function (serviceId) {
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

exports.save = function (_cas) {
  let cas = Object.assign({}, _cas)

  cas.request.queries = utils.arrToObj(cas.request.queries)
  cas.request.headers = utils.arrToObj(cas.request.headers)
  cas.response.headers = utils.arrToObj(cas.response.headers)

  console.log('#cas:', cas)

  if (cas.id) {
    return this.modify(cas.id, cas)
      .then(res => {
        console.log('###res.body:', res.body)
        return cas.id
      })
  } else {
    return this.create(cas)
      .then(res => res.body.id)
  }
}

exports.create = function (cas) {
  console.log('#create:', cas)
  return superagent.post(restUrl)
    .send(cas)
}

exports.modify = function (id, cas) {
  console.log('#modify:', cas)
  delete cas.createdBy
  delete cas.createdDate
  delete cas.lastModifiedBy
  delete cas.lastModifiedDate
  return superagent.post(restUrl)
    .send(cas)
    .then(res => {
      console.log('#res.body:', res.body)
      return res
    })
}

exports.detail = function (id) {
  return superagent.get(restUrl + '/' + id + '?projection=inline')
    .then(res => res.body)
}

exports.findOneWithArray = function (id) {
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

exports.remove = function (id) {
  return superagent.delete(restUrl + '/' + id)
}

exports.saveResponse = function (cas) {
  return superagent.put(`${restUrl}/${cas.id}/response`)
    .send(cas.response)
}

exports.recordResponse = function (id) {
  return superagent.patch(restUrl + '/' + id + '/recordResponse')
    .then(res => res.body)
}
