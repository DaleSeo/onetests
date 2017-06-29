import superagent from 'superagent'
import utils from './utils'
import config from '../../config'

const restUrl = config.BACKEND_URL

export default function callApi (request, environment) {
  let req = Object.assign({}, request)
  console.log(req)

  let environmentId = environment && environment.id || ''
  return superagent.post(restUrl + '/callApi?environmentId=' + environmentId)
    .send(req)
    .then(res => res.body)
    .catch(err => {
      console.error(err)
      throw err
    })
}
