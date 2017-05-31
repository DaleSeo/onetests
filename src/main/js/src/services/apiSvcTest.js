import apiSvc from './apiSvc'

// apiSvc.list('5912a299ec46ff6c417a9482')
//   .then(apis => console.log(apis))

apiSvc.save({
  id: '',
  title: 'title',
  method: 'GET',
  path: '',
  body: '',
  description: '',
  exclusions: [],
  serviceId: this.id,
  skip: false
})
.then(res => console.log(res))

// apiSvc.detail('5912a2bfec46ff6c417a9484')
//   .then(service => console.log(service))
