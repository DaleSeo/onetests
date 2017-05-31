import db from '../../src/services/database'

// db.ref('tests')
//   .orderByChild('suiteKey')
//   .equalTo('-KhbS1F4xQzmJIv0Xnpi')
//   .once('value')
//   .then(value => {
//     console.log(value.toJSON())
//     db.goOffline()
//   })

// db.ref('apis/-KhQ6IOWQKJZbqjexNDj/specs')
//   .once('value')
//   .then(specs => {
//     specs.forEach(spec => {
//       db.ref('apis/-KhQ6IOWQKJZbqjexNDj/apis').push(spec.val())
//     })
//   })

db.ref('apis/-KhQ6IOWQKJZbqjexNDj/apis')
  .orderByChild('method')
  .equalTo('GET')
  .once('value')
  .then(specs => {
    specs.forEach(spec => {
      console.log(spec.key)
      db.ref('apis/-KhQ6IOWQKJZbqjexNDj/apis').child(spec.key).remove()
    })
  })
