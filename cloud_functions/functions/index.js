const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

// // Take the text parameter passed to this HTTP endpoint and insert it into the
// // Realtime Database under the path /messages/:pushId/original
// exports.addMessage = functions.https.onRequest((request, response) => {
//   // Grab the text parameter.
//   const title = request.query.title;
//   const message = request.query.message;
//   // Push it into the Realtime Database then send a response
//   admin.database().ref('/messages').push({title: title, message: message}).then(snapshot => {
//     // Redirect with 303 SEE OTHER to the URL of the pushed object in the Firebase console.
//     response.redirect(303, snapshot.ref);
//   });
// });

exports.pushNotification = functions.database.ref('/messages/{pushId}').onWrite( event => {

    console.log('Push notification event triggered');

    //  Grab the current value of what was written to the Realtime Database.
    var valueObject = event.data.val();

    var registrationToken = valueObject.registrationToken;
        
    const payload = {
        notification: {
            title: 'Cafeteria Application',
            body: "New message",
            sound: "default"
        },
        data: {
            title: valueObject.title,
            message: valueObject.message
        }
    };
 
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24 //24 hours
    };

    // Send a message to the device corresponding to the provided
    // registration token.
    try{
        return admin.messaging().sendToDevice(registrationToken, payload)
            .then(function(response) {
                // See the MessagingDevicesResponse reference documentation for
                // the contents of response.
                console.log("Successfully sent message to ", registrationToken);
                return true;
            });
    } catch (err) {
        console.log("Error sending message:", error);
        return Promise.reject(err);
    }
    //return admin.messaging().sendToTopic("notifications", payload, options);
});