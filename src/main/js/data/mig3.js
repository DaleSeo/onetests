import db from '../src/services/database'

let apiRef = db.ref('services/-KhQ6IOWQKJZbqjexNDj/apis')

apiRef.once('value')
  .then(apis => {
    let keys = []
    apis.forEach(api => {
      keys.push(api.key)
    })
    return keys
  })
  .then(keys =>
    keys.map(key =>
      apiRef.child(key).child('exclusion').once('value')
        .then(exclusion => exclusion.val() ? exclusion.val().split(',') : [])
        .then(exclusions => apiRef.child(key).child('exclusions').set(exclusions))
        .then(_ => apiRef.child(key).child('exclusion').remove())
    )
  )
  .then(promises => Promise.all(promises))
  
  .catch(err => console.error(err))
  .then(_ => db.goOffline())
