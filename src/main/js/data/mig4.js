import db from '../src/services/database'

let apiRef = db.ref('services/-KhQ6MTyGDA-W1CaALev/apis')

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
        .then(exclusions => apiRef.child(key).child('exclusions').set(exclusions.val()))
        .then(_ => apiRef.child(key).child('exclusion').remove())
    )
  )
  .then(promises => Promise.all(promises))
  .then(_ => console.log('done'))
  .catch(err => console.error(err))
  .then(_ => db.goOffline())
