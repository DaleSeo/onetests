<template>
  <div class="ui stackable fixed inverted menu">
    <div class="ui container">
      <div class="header item">
        <h1 class="ui teal inverted header">ONE tests</h1>
      </div>
      <a href="/" class="red item">
        <i class="home icon"/>Home
      </a>
      <a href="/console" class="item">
        <i class="terminal icon"/>Console
      </a>
      <a href="/cases" class="item">
        <i class="cube icon"/>Cases
      </a>
      <a href="/suites" class="item">
        <i class="cubes icon"/>Suites
      </a>
      <a href="/calls" class="item">
        <i class="history icon"/>History
      </a>
      <a href="/dashboard" class="item">
        <i class="dashboard icon"/>Dashboard
      </a>
      <div class="ui right dropdown item">
        <i class="user icon"/>Profile<i class="dropdown icon"/>
        <div class="menu">
          <a href="/profile" class="item">
            <i class="user icon"/>My Page
          </a>
          <a href="/settings" class="item">
            <i class="settings icon"/>Settings
          </a>
          <a href="/logout" class="item">
            <i class="sign out icon"/>Logout
          </a>
        </div>
      </div>
    </div>
  </div>
</template>
