'use strict'

const functions = require("firebase-functions");
const nodemailer = require("nodemailer");
const db = admin.firestore();

const admin = require('firebase-admin');
const { firebaseConfig } = require("firebase-functions");
admin.initializeApp();

const gmailEmail = "techiebuddy20@gmail.com"
const gmailPassword = "9vH!CA5!nr%kXSHynfBS"
const mailTransport = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: gmailEmail,
        pass: gmailPassword
    }
});

const actionCodeSettings = {
    // URL you want to redirect back to. The domain (www.example.com) for
    // this URL must be whitelisted in the Firebase Console.
    url: 'https://chatmatch-b8ec9.web.app',
    // This must be true for email link sign-in.
    handleCodeInApp: false,
};


// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions


// exports.userSignedUp = functions.auth.user().onCreate((user) => {
//     // Email of the user
//     const email = user.email;
//     const displayName = user.displayName;

//     // Send a welcome email
//     return sendWelcomeEmail(email, displayName);

// });

exports.disableUser = functions.auth.user().onCreate((user) => {
    // Email of the user
    const uid = user.uid;

    if (user.email.endsWith("outlook.com")) {
        admin.auth().updateUser(uid, {
            disabled: true
        })
            .then((user) => {
                functions.logger.log('Disable user account', user.email);
            })
            .catch((error) => {
                functions.logger.log('Error disabling user', error);
            })
    }
    return null;

});



exports.verifyUser = functions.auth.user().onCreate(user => {
    // Email of the user
    const email = user.email;
    const displayName = user.displayName;

    admin.auth().generateEmailVerificationLink(email, actionCodeSettings)
        .then((link) => {
            return sendVerificationEmail(email, displayName, link);
        })
        .catch((error) => {
            console.log(error);
        })

    return null;
});



async function sendWelcomeEmail(email, displayName) {
    const mailOptions = {
        from: 'Excite <techiebuddy20@gmail.com>',
        to: email,
        subject: "Welcome to Excite",
        text: `Hey ${displayName || ''}! Welcome to Excite. We hope you will enjoy our service`
    };

    await mailTransport.sendMail(mailOptions);
    functions.logger.log('Welcome Email sent to:', email);
    return null;
}

async function sendVerificationEmail(email, displayName, link) {

    const htmlMessage = `Hello, <br><br>
    Follow this link to verify your email address: <br><br> 
    <a href='${link}'>${link}</a> <br><br>
    If you didn???t ask to verify this address, you can ignore this email. <br><br>
    Thanks, <br><br>
    Your Excite team <br><br>
    `


    const mailOptions = {
        from: 'Excite <techiebuddy20@gmail.com>',
        to: email,
        subject: "Welcome to Excite! Please verify your email.",
        html: htmlMessage
    };

    await mailTransport.sendMail(mailOptions);
    functions.logger.log('Welcome Email sent to:', email);
    return null;
}



exports.createMatches = functions.firestore.document('users/{userID}').onWrite((change, context) > {

    // Generate 10 specific matches
    // Write the matches' user ids in the user array "matches"

})

const getUsers = () => {
    return db.collection('users').get().then(
        querySnapshot => {
            const users = [];
            querySnapshot.forEach(doc => {
                users.push({
                    ...doc.data(),
                    id: doc.id,
                    ref: doc.ref
                });
            });
            return users;
        });
};