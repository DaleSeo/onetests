import _ from 'lodash'

exports.arrToObj = function (array) {
  return array.reduce((prev, curr) => {
    prev[curr.key] = curr.value
    return prev
  }, {})
}

exports.objToArr = function (obj) {
  return _.entries(obj)
    .map(([key, value]) => ({key, value}))
}

exports.methodClass = function (method) {
  switch (method) {
    case 'GET': return 'label-success'
    case 'POST': return 'label-info'
    case 'PUT': return 'label-warning'
    case 'DELETE': return 'label-danger'
    default: return 'label-primary'
  }
}
