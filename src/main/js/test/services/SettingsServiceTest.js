import SettingsService from '../../src/services/SettingsService'

let svc = new SettingsService()

svc.findHeadersExclusions()
  .then(exclusions => {
    console.log(exclusions)
  })

svc.findBodyExclusions()
  .then(exclusions => {
    console.log(exclusions)
  })

svc.findGlobalExclusions()
  .then(exclusions => {
    console.log(exclusions)
  })
