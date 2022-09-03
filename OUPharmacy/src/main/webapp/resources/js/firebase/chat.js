/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

import { initializeApp } from "https://www.gstatic.com/firebasejs/9.9.3/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.9.3/firebase-analytics.js";
import { getDatabase, ref, set, get,
        child, onValue, push, onChildAdded, onChildChanged,
        query, limitToLast, equalTo, orderByValue, startAt,
        } from "https://www.gstatic.com/firebasejs/9.9.3/firebase-database.js";

const firebaseConfig = {
    apiKey: "AIzaSyDI-WFb2Mn-JzHjHrBZNzBnv7FUfv8BFV4",
    authDomain: "oupharmacy-5ddaa.firebaseapp.com",
    databaseURL: "https://oupharmacy-5ddaa-default-rtdb.firebaseio.com",
    projectId: "oupharmacy-5ddaa",
    storageBucket: "oupharmacy-5ddaa.appspot.com",
    messagingSenderId: "999793746685",
    appId: "1:999793746685:web:311028632b737f551d5494",
    measurementId: "G-JB59G5JCQH"
};
// ===== Write Data =====
const writeUserData = (userId, name, avatar) => {
    set(child(dbRef, `users/${userId}`), {
        id: userId,
        name: name,
        avatar: avatar,
    });
};
const writeMessageData = (content, userId) => {
    set(child(dbRef, `users/${userId}`), {
        id: Math.floor(Math.random() * 100),
        content: content,
        userId: userId,
    });
};

const readUserData = (userId) => {
    get(child(dbRef, `users/${userId}`)).then((snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val());
        } else {
            console.log("No data available");
        }
    }).catch((error) => {
        console.error(error);
    });
};

const dbRef = ref(getDatabase());

// get or create user
const getOrCreateUser = (userId, name, avatar) => {
    try {
        get(child(dbRef, `users/${userId}`)).then((user) => {
            if (!user.exists()) {
                writeUserData(userId, name, avatar);
            }
        });
        return userId;
    } catch (err) {
        return null;
    }
};






