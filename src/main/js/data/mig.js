import db from '../src/services/database'

db.ref('suites')
  .child('-Ki4asFnCJdK8F_7yK3C')
  .once('value')
  .then(snap => snap.val())
  .then(suite =>
    db.ref('services')
      .child('-KhQ6IOWQKJZbqjexNDj')
      .child('suites')
      .push(suite)
  )
  .then(snap => snap.key)
  .then(key => console.log(key))
  .then(_ => db.goOffline())

// db.ref('services')
//   .once('value')
//   .then(snap => snap.val())
//   .then(services => Object.keys(services))
//   .then(keys =>
//     keys.map(key =>
//       db.ref('services')
//         .child(key)
//         .child('specs')
//         .once('value')
//         .then(snap => snap.val())
//         .then(specs =>
//           db.ref('services')
//             .child(key)
//             .child('apis')
//             .set(specs)
//         )
//         .then(_ =>
//           db.ref('services')
//             .child(key)
//             .child('specs')
//             .remove()
//         )
//
//   ))
//   .then(promises => Promise.all(promises))
//   .then(values => console.log(values))
//   .catch(err => console.log(err))
//   .then(_ => db.goOffline())

// db.ref('apis')
//   .once('value')
//   .then(snap => snap.val())
//   .then(apis => db.ref('services').set(apis))
//   .then(_ => db.ref('apis').remove())
//   .then(_ => db.goOffline())
