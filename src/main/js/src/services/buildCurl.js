import querystring from 'querystring'
import _ from 'lodash'
import utils from './utils'

export default function buildCurl (req) {
  console.log('#req:', req)

  let url = req.host + req.path

  let queryString = buildQueryString(req.queries)
  if (queryString) {
    url += '?' + queryString
  }

  let curl = `curl -X ${req.method} '${url}'`

  let headerString = buildHeaderString(req.headers)
  if (headerString) {
    curl += headerString
  }

  if (req.body) {
    curl += ` -d '${req.body}'`
  }
  return curl
}

function buildQueryString (queries) {
  if (!queries) {
    return null
  }
  let queryObject = utils.arrToObj(queries)
  return querystring.stringify(queryObject)
}

function buildHeaderString (headers) {
  if (!headers) {
    return null
  }
  let headerObject = utils.arrToObj(headers)

  let headerString = ''
  _.entries(headerObject).forEach(([key, value]) => {
    headerString += ` -H '${key}: ${value}' `
  })
  return headerString
}
