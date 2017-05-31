import Equaler from './Equaler'

export default function diffResponse (resA, resB, options) {
  return {
    statusEqual: diffStatus(resA.statusCode, resB.statusCode),
    headersEqual: diffHeaders(resA.headers, resB.headers, options && options.headersExclusions),
    bodyEqual: diffBody(resA.body, resB.body, options && options.bodyExclusions)
  }
}

function diffStatus (statusA, statusB) {
  return statusA === statusB
}

function diffHeaders (headersA, headersB, exclusions) {
  return new Equaler(exclusions).isEqual(headersA, headersB)
}

function diffBody (bodyA, bodyB, exclusions) {
  return new Equaler(exclusions).isEqual(bodyA, bodyB)
}
