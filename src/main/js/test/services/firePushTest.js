import db from '../../src/services/database'

db.ref('exams')
  .push(true)
  .then(val => {
    console.log(val.key)
    db.goOffline()
  })
