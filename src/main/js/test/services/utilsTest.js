import utils from '../../src/services/utils'

let obj = {
  'Accept': 'application/json;charset=UTF-8',
  'Content-type': 'application/json;charset=UTF-8'
}
let arr = utils.objToArr(obj)
console.log('#arr:', arr)

utils.objToArr(undefined)
