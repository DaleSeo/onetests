import db from '../src/services/database'

db.ref('services')
  .once('value')
  .then(snap => snap.val())
  .then(services => Object.keys(services))
  .then(serviceKeys =>
    serviceKeys.forEach(key =>
      db.ref('services').child(key).child('apis')
        .once('value')
        .then(snap => snap.val())
        .then(apis => {
          if (!apis) return
          Object.keys(apis).map(apiKey =>
            db.ref('services').child(key)
              .child('apis').child(apiKey)
              .child('url')
              .remove()
          )
        })
    )
  )
