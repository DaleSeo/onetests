import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import VueFire from 'vuefire'
import VueClipboard from 'vue-clipboard2'
import moment from 'moment'
import App from './App.vue'

Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(VueFire)
Vue.use(VueClipboard)
Vue.filter('formatDate', function (date) {
  return moment(date).format('YY/MM/DD HH:mm:ss')
})
Vue.filter('userName', function (user) {
  return (user && user.name) || ''
})

// import 'bootstrap/dist/css/bootstrap.min.css'
// import 'bootstrap/dist/js/bootstrap.min.js'

import '../semantic/dist/semantic.min.css'
import '../semantic/dist/semantic.min.js'

import 'toastr/build/toastr.min.css'
import 'toastr/build/toastr.min.js'

import './assets/js/dataTables.semanticui.min.js'
import './assets/css/dataTables.semanticui.min.css'

import Index from './components/home/Index.vue'
import Test from './components/test/Index.vue'

import TestDetail from './components/test/TestDetail.vue'
import Settings from './components/settings/Settings.vue'

import Console from './components/console/Index.vue'
import Case from './components/case/Index.vue'
import CaseEdit from './components/case/Edit.vue'
import CaseDetail from './components/case/Detail.vue'

import Suite from './components/suite/Index.vue'
import SuiteEdit from './components/suite/Edit.vue'
import SuiteDetail from './components/suite/Detail.vue'

import Call from './components/call/Index.vue'
import CallDetail from './components/call/Detail.vue'

import Dashboard from './components/dashboard/Index.vue'
import Login from './components/user/Login.vue'
import Signup from './components/user/Signup.vue'
import Profile from './components/user/Profile.vue'
import Admin from './components/admin/Index.vue'

import NotFound from './components/NotFound.vue'

const router = new VueRouter({
  mode: 'history',
  routes: [
    {path: '/', component: Index},
    {path: '/test', component: Test},
    {path: '/services/:serviceId/suites/:suiteId', component: SuiteDetail, props: true},
    {path: '/tests/:suiteId/:testId', component: TestDetail, props: true},
    {path: '/settings', component: Settings},
    {path: '/console', component: Console},
    {path: '/cases', component: Case},
    {path: '/cases/new', component: CaseEdit},
    {path: '/cases/:id', component: CaseDetail, props: true},
    {path: '/cases/:id/edit', component: CaseEdit, props: true},
    {path: '/suites', component: Suite},
    {path: '/suites/new', component: SuiteEdit},
    {path: '/suites/:id', component: SuiteDetail, props: true},
    {path: '/calls', component: Call},
    {path: '/calls/:id', component: CallDetail, props: true},
    {path: '/dashboard', component: Dashboard},
    {path: '/login', component: Login},
    {path: '/signup', component: Signup},
    {path: '/profile', component: Profile},
    {path: '/admin/:category', component: Admin, props: true},
  ]
})

new Vue({
  router,
  template: '<App/>',
  components: {App}
}).$mount('#app')
