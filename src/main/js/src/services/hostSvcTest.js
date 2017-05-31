import hostSvc from './hostSvc'

// hostSvc.list()
//   .then(services => console.log(services))

hostSvc.create({
  baseUrl: "http://www.test1.com",
  serviceId: "5912a282ec46ff6c417a9481",
  title: "Host #1"
})
.then(res => console.log(res))

// hostSvc.detail('5912a2bfec46ff6c417a9484')
//   .then(service => console.log(service))
