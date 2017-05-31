import SuiteService from '../../src/services/SuiteService'
import db from '../../src/services/database'

describe('SuiteService', function () {
  let suiteService

  before(function() {
    suiteService = new SuiteService()
  })

  it('#create', function() {
    suiteService.create()
  })
})
