import Equaler from '../../src/services/Equaler'
import assert from 'assert'

let equaler = new Equaler(['date', 'time'])

// number
assert(equaler.isEqual(1, 1))
assert(!equaler.isEqual(1, 2))

// string
assert(equaler.isEqual('TEXT', 'TEXT'))
assert(!equaler.isEqual('TEXT', 'TEST'))

// undefined & null
assert(equaler.isEqual(undefined, undefined))
assert(equaler.isEqual(null, null))

// Array
assert(equaler.isEqual(['a', 'b'], ['a', 'b']))
assert(!equaler.isEqual(['a', 'b'], ['a', 'b', 'c']))
assert(equaler.isEqual([1, 'TEXT', {b: 2}], [1, 'TEXT', {b: 2}]))
assert(!equaler.isEqual([1, 'TEXT', {b: 2}], [1, 'TEXT', {b: 3}]))

// Object
assert(equaler.isEqual({}, {}))
assert(!equaler.isEqual({}, {a: 2}))
assert(equaler.isEqual({a: 1, date: 'Tue, 18 Apr 2017 02:16:21 GMT'}, {a: 1, date: 'Tue, 17 Apr 2017 02:16:21 GMT'}))
assert(!equaler.isEqual({a: 1, date1: 'Tue, 18 Apr 2017 02:16:21 GMT'}, {a: 1, date1: 'Tue, 17 Apr 2017 02:16:21 GMT'}))
assert(equaler.isEqual({a: 1, b: {c: 'text'}, d: [2, 'ttt']}, {a: 1, b: {c: 'text'}, d: [2, 'ttt']}))
assert(!equaler.isEqual({a: 1, b: {c: 'text'}, d: [2, 'ttt']}, {a: 1, b: {c: 'text'}, d: [2, 'ttT']}))
