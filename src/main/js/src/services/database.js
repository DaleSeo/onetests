import Firebase from 'firebase'

export default Firebase.initializeApp({
  databaseURL: 'https://api-diff.firebaseio.com'
}).database()
