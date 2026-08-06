// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyBRd5s4YWJvQ7T0CLXXCkDJ75C2_duJQNw",
  authDomain: "simple-events-a7e71.firebaseapp.com",
  projectId: "simple-events-a7e71",
  storageBucket: "simple-events-a7e71.firebasestorage.app",
  messagingSenderId: "981002869435",
  appId: "1:981002869435:web:3ebe41f0b66bc4dd5f679e",
  measurementId: "G-YPXRLFSVZP"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
