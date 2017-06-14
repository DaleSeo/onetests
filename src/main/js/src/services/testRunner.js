import superagent from 'superagent'
import utils from './utils'
import config from '../../config'

const restUrl = config.BACKEND_URL

exports.runCase = function (caseId) {
  console.log('#runCase:', caseId)
  return superagent.post(restUrl + `/cases/${caseId}/run`)
    .then(res => res.body)
    .then(result => {
      console.log('#result:', result)
      return result
    })
    .catch(err => {
      console.error(err)
      throw err
    })
}

exports.runSuite = function (suiteId) {
  console.log('#runSuite:', suiteId)
  return superagent.post(restUrl + `/suites/${suiteId}/run`)
    .then(res => res.body)
    .then(result => {
      console.log('#result:', result)
      return result
    })
    .catch(err => {
      console.error(err)
      throw err
    })
}
