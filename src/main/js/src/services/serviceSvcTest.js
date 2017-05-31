import serviceSvc from './serviceSvc'

// serviceSvc.list()
//   .then(services => console.log(services))

// serviceSvc.create({
//   code: 'JPH',
//   name: 'Jsonplaceholder',
//   description: ''
// })
// .then(res => console.log(res))

serviceSvc.detail('5912a2bfec46ff6c417a9484')
  .then(service => console.log(service))
