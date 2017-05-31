import Vue from 'vue'
import VueRouter from 'vue-router'
import VueFire from 'vuefire'
import VueClipboard from 'vue-clipboard2'
import moment from 'moment'
import App from './App.vue'

Vue.use(VueRouter)
Vue.use(VueFire)
Vue.use(VueClipboard)
Vue.filter('formatDate', function (date) {
    return moment(date).format('YY/MM/DD HH:mm:ss')
})

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

import '../semantic/dist/semantic.min.css'
import '../semantic/dist/semantic.min.js'

import Index from './components/Index.vue'
import Service from './components/service/Service.vue'
// import SuiteDetail from './components/suite_bak/SuiteDetail.vue'
import TestDetail from './components/test/TestDetail.vue'
import Settings from './components/settings/Settings.vue'

import Console from './components/console/Index.vue'
import Case from './components/case/Index.vue'
import CaseDetail from './components/case/Detail.vue'

import Suite from './components/suite/Index.vue'
import SuiteEdit from './components/suite/Edit.vue'
import SuiteDetail from './components/suite/Detail.vue'

import Call from './components/history/Index.vue'
import CallDetail from './components/history/Detail.vue'

import Dashboard from './components/dashboard/Index.vue'

import NotFound from './components/NotFound.vue'

const router = new VueRouter({
  mode: 'history',
  routes: [
    {path: '/', component: Index},
    {path: '/services/:id', component: Service, props: true},
    {path: '/services/:serviceId/suites/:suiteId', component: SuiteDetail, props: true},
    {path: '/tests/:suiteId/:testId', component: TestDetail, props: true},
    {path: '/profile', component: NotFound},
    {path: '/settings', component: Settings},
    {path: '/console', component: Console},
    {path: '/cases', component: Case},
    {path: '/cases/:id', component: CaseDetail, props: true},
    {path: '/suites', component: Suite},
    {path: '/suites/new', component: SuiteEdit},
    {path: '/calls', component: Call},
    {path: '/calls/:id', component: CallDetail, props: true},
    {path: '/suites/:id', component: SuiteDetail, props: true},
    {path: '/dashboard', component: Dashboard}
  ]
})

new Vue({
  router,
  template: '<App/>',
  components: {App}
}).$mount('#app')
