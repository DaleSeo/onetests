import db from './database'

export default class SettingsService {

  constructor () {
    this.settingsRef = db.ref('settings')
  }

  findGlobalExclusions () {
    return Promise.all([
      this.findHeadersExclusions(),
      this.findBodyExclusions()
    ])
    .then(results => {
      return {
        headersExclusions: results[0],
        bodyExclusions: results[1]
      }
    })
  }

  findHeadersExclusions () {
    return this.settingsRef.child('headersExclusions').once('value')
      .then(snap => snap.val())
  }

  findBodyExclusions () {
    return this.settingsRef.child('bodyExclusions').once('value')
      .then(snap => snap.val())
  }

}
