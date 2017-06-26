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

  url += '&projection=inline'

  console.log(url)
  return superagent.get(url)
    .then(res => res.body._embedded.cases)
}

exports.save = function (_cas) {
  let cas = Object.assign({}, _cas)

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
  return superagent.patch(`${restUrl}/${cas.id}`)
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

exports.saveBasics = function (cas) {
  return superagent.put(`${restUrl}/${cas.id}/basics`)
    .send(cas)
}

exports.saveRequest = function (cas) {
  return superagent.put(`${restUrl}/${cas.id}/request`)
    .send(cas.request)
}

exports.saveResponse = function (cas) {
  return superagent.put(`${restUrl}/${cas.id}/response`)
    .send(cas.response)
}

exports.recordResponse = function (id) {
  return superagent.patch(restUrl + '/' + id + '/recordResponse')
    .then(res => res.body)
}

exports.clone = function (id) {
  return superagent.post(`${restUrl}/${id}/clone`)
    .then(res => res.body)
}
