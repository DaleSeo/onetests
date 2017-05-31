import diffResponse from '../../src/services/diffResponse'
import assert from 'assert'

let resA = {
  statusCode: 200,
  statusText: 'Created',
  headers: {
    'cache-control': 'no-cache',
    'content-type': 'application/json; charset=utf-8',
    'expires': '-1',
    'pragma': 'no-cache',
    'date': 'Tue, 18 Apr 2017 02:16:21 GMT'
  },
  body: {
    "code" : "EC_ISF_9999",
    "message" : "ISF 연동 오류",
    "hostName" : "qasp-ec",
    "instanceName" : "sp-ecOneSvr1",
    "cause" : {
      "code" : "EC_SYS_ERROR_NET_TIMEOUT_CON",
      "message" : "서버연결타임아웃이 발생했습니다.",
      "hostName" : "qasp-ec",
      "instanceName" : "sp-ecOneSvr1"
    }
  }
}

let resB = {
  statusCode: 200,
  statusText: undefined,
  headers: {
    'cache-control': 'no-cache',
    'content-type': 'application/json; charset=utf-8',
    'expires': '-1',
    'pragma': 'no-cache',
    'date': 'Tue, 17 Apr 2017 02:16:21 GMT'
  },
  body: {
    "code" : "EC_ISF_9999",
    "message" : "ISF 연동 오류",
    "hostName" : "qasp-ec",
    "instanceName" : "sp-ecOneSvrFwUp",
    "cause" : {
      "code" : "EC_SYS_ERROR_NET_TIMEOUT_CON",
      "message" : "서버연결타임아웃이 발생했습니다.",
      "hostName" : "qasp-ec",
      "instanceName" : "sp-ecOneSvrFwUp"
    }
  }
}

let options = {
  headersExclusions: ['date', 'x-sac-guid'],
  bodyExclusions: ['hostName', 'instanceName']
}

let result = diffResponse(resA, resB, options)
console.log('#result:', result)

assert(result.statusEqual)
assert(result.headersEqual)
assert(result.bodyEqual)
