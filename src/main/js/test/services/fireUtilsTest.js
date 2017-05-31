import { fireGet, firePush, fireRemove } from '../../src/services/fireUtils'

// fireGet('apis/-KhQ6IOWQKJZbqjexNDj')
//   .then(data => {
//     console.log(data)
//   })

firePush('exams', {a: '1'})
  .then(key => {
    console.log(key)
    fireRemove('exams/' + key)
      .then(_ => {
        console.log('pushed and removed')
      })
  })
