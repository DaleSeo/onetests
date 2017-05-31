import callApi from '../../src/services/callApi'

callApi({
  method: 'DELETE',
  url: 'http://jsonplaceholder.typicode.com/posts/1'
}).then(res => {
  console.log(res)
}).catch(err => {
  console.log(err)
})

// callApi({
//   method: 'DELETE',
//   url: 'http://1jsonplaceholder.typicode.com/posts/1'
// }).then(res => {
//   console.log(res)
// }).catch(err => {
//   console.log(err)
// })
