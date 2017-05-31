import db from '../../src/services/database'

// db.ref('suites/-KhbS1F4xQzmJIv0Xnpi').child('tests').remove()
//   .then(_ => {
//     db.goOffline()
//   })

db.ref('tests')
  .orderByChild('suiteKey')
  .equalTo('-KhbS1F4xQzmJIv0Xnpi')
  .once('value')
  .then(tests => {
    tests.forEach(test => {
      db.ref('tests').child(test.key).remove()
    })
  })
