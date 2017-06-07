import superagent from 'superagent'
import utils from './utils'
import config from '../../config'

const restUrl = config.BACKEND_URL

exports.runSingle = function (caseId) {
  console.log('#runSingle:', caseId)
  return superagent.post(restUrl + `/cases/${caseId}/run`)
    .then(res => res.body)
    .then(call => {
      console.log('#call:', call)
      return call
    })
    .catch(err => {
      console.error(err)
      throw err
    })
}
