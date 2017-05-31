import db from './database'

export default class SuiteService {

  constructor (serviceId) {
    this.suiteRef = db.ref(`services/${serviceId}/suites`)
  }

  create (suite) {
    console.log('SuiteService#create')
    return this.suiteRef.parent.child('apis').once('value')
      .then(snap => snap.val())
      .then(apis => Object.values(apis).filter(api => !api.skip))
      .then(apis => {
        suite.apis = apis
        suite.date = new Date().getTime()
        return suite
      })
      .then(suite => this.suiteRef.push(suite))
      .then(snap => snap.key)
  }

  remove (id) {
    console.log('SuiteService#remove')
    return this.suiteRef
      .child(id)
      .remove()
  }

  find (id) {
    console.log('SuiteService#find')
    return this.suiteRef
      .child(id)
      .once('value')
      .then(snap => snap.val())
  }

  modify (id, key, value) {
    console.log('SuiteService#modify')
    console.log(id, key, value)
    return this.suiteRef
      .child(id)
      .child(key)
      .set(value)
  }

}
