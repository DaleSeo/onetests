exports.arrayToObj = function (array) {
  return array.reduce((prev, curr) => {
    prev[curr.key] = curr.value
    return prev
  }, {})
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
