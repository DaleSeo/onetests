import superagent from 'superagent'
import utils from './utils'
import config from '../../config'

const restUrl = config.BACKEND_URL

export default function callApi (request, caseId) {
  let req = Object.assign({}, request)
  //req.queries = utils.arrToObj(req.queries)
  req.headers = utils.arrToObj(req.headers)
  console.log(req)

  return superagent.post(restUrl + '/callApi?caseId=' + caseId)
    .send(req)
    .then(res => res.body)
    .catch(err => {
      console.error(err)
      throw err
    })
}
