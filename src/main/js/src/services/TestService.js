import callApi from './callApi'
import diffResponse from './diffResponse'
import SettingsService from './SettingsService'

import db from './database'

export default class TestService {

  constructor (suiteId) {
    this.testRef = db.ref('tests').child(suiteId)
  }

  initialize (suite) {
    console.log('TestService#initialize')
    console.log(suite)
    suite.apis.forEach(api => {
      console.log('api:', api)
      console.log(this.testRef.toString())
      console.log({
        api,
        hostA: suite.hostA,
        hostB: suite.hostB,
      })
      this.testRef.push({
        api,
        hostA: suite.hostA,
        hostB: suite.hostB,
      })
    })
  }

  prep () {
    console.log('TestService#prep')
    return this.testRef
      .once('value')
      .then(tests => {
        tests.forEach(test => {
          let reqA = {
            method: test.val().api.method,
            url: test.val().hostA + test.val().api.path,
            body: test.val().api.body || {},
            exclusions: test.val().api.exclusions || []
          }
          let reqB = {
            method: test.val().api.method,
            url: test.val().hostB + test.val().api.path,
            body: test.val().api.body || {},
            exclusions: test.val().api.exclusions || []
          }
          this.testRef.child(test.key).child('reqA').set(reqA)
          this.testRef.child(test.key).child('reqB').set(reqB)
        })
      })
  }

  call () {
    console.log('TestService#call')
    return this.testRef
      .once('value')
      .then(tests =>
        tests.forEach(test => {
          callApi(test.val().reqA)
            .then(res => this.testRef.child(test.key).child('resA').set(res))
            .catch(err => this.testRef.child(test.key).child('errA').set(err))
          callApi(test.val().reqB)
            .then(res => this.testRef.child(test.key).child('resB').set(res))
            .catch(err => this.testRef.child(test.key).child('errB').set(err))
        })
      )
  }

  diff () {
    console.log('TestService#diff')
    new SettingsService().findGlobalExclusions()
      .then(globalExclusions => {
        this.testRef
          .once('value')
          .then(tests => {
            tests.forEach(test => {
              let options = {
                headersExclusions: globalExclusions.headersExclusions.concat(test.val().reqA.exclusions),
                bodyExclusions: globalExclusions.bodyExclusions.concat(test.val().reqA.exclusions)
              }
              let result = diffResponse(test.val().resA, test.val().resB, options)
              this.testRef.child(test.key).child('result').set(result)
            })
          })
      })
  }

  aggregate () {
    console.log('TestService#aggregate')
    return this.testRef
      .once('value')
      .then(tests => {
        let [numPass, numFail] = [0, 0]
        tests.forEach(test => {
          if (test.val().result
            && test.val().result.statusEqual
            && test.val().result.headersEqual
            && test.val().result.bodyEqual) {
            numPass++
          } else {
            numFail++
          }
        })
        return {numPass, numFail}
      })
  }

}
