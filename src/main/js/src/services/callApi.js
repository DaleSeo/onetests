import superagent from 'superagent'
import utils from './utils'
import config from '../config'

const restUrl = config.BACKEND_URL

export default function callApi (request, apiId) {
  let req = Object.assign({}, request)
  req.queries = utils.arrayToObj(req.queries)
  req.headers = utils.arrayToObj(req.headers)
  console.log(req)

  return superagent.post(restUrl + '/callApi?apiId=' + apiId)
    .send(req)
    .then(res => {
      return {
        statusCode: res.body.statusCode,
        statusMessage: res.body.statusMessage,
        headers: res.body.headers,
        body: res.body.body,
        text: res.body.text
      }
    })
    .then(res => {
      console.log(res)
      return res
    })
    .catch(err => {
      console.error(err)
      throw err
    })
}
