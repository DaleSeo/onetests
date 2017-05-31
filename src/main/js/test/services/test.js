let options = {
  headersExclusions: ['AAA', 'aaa'],
  bodyExclusions: ['BBB', 'bbb']
}

let globalExclusions = {
  headersExclusions: [ 'guid' ],
  bodyExclusions: [ 'date', 'test' ]
}

options.headersExclusions = options.headersExclusions.concat(globalExclusions.headersExclusions)
options.bodyExclusions = options.bodyExclusions.concat(globalExclusions.bodyExclusions)
console.log(options)
