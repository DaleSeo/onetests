import _ from 'lodash'

export default class Equaler {

  constructor (exclusions) {
    this.exclusions = exclusions
  }

  isEqual (valA, valB) {
    if (typeof valA !== typeof valB) {
      return false
    }
    if (Array.isArray(valA)) {
      return this.isArrayEqual(valA, valB)
    }
    if (valA instanceof Object) {
      return this.isObjectEqual(valA, valB)
    }
    return valA === valB
  }

  isArrayEqual (arrA, arrB) {
    if (arrA.length !== arrB.length) {
      return false
    }
    for (let idx in arrA) {
      if (!this.isEqual(arrA[idx], arrB[idx])) {
        return false
      }
    }
    return true
  }

  isObjectEqual (objA, objB) {
    if (this.exclusions) {
      this.exclusions.forEach(key => {
        delete objA[key]
        delete objB[key]
      })
    }
    if (Object.keys(objA).length !== Object.keys(objB).length) {
      console.log('NotSameLength:', Object.keys(objA).length, 'vs.', Object.keys(objB).length)
      return false
    }
    for (let key in objA) {
      if (!_.has(objB, key)) {
        console.log('HasNot ', key, ':', objA[key], 'vs.', objB[key])
        return false
      }
      if (!this.isEqual(objA[key], objB[key])) {
        console.log('NotEqual ', key, ':', objA[key], 'vs.', objB[key])
        return false
      }
    }
    return true
  }

}
