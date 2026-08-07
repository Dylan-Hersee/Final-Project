import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuth, signInWithEmailAndPassword, createUserWithEmailAndPassword } from "firebase/auth";
import { auth } from "../firebase";




const Login = () => {
  const [loginEmail, setLoginEmail] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [loginError, setLoginError] = useState("");


  const [signUpEmail, setSignUpEmail] = useState("");
  const [signUpPassword, setSignUpPassword] = useState("");
  const [signUpConfirmPassword, setSignUpConfirmPassword] = useState("");
  const [signUpFirstName, setSignUpFirstName] = useState("");
  const [signUpSecondName, setSignUpSecondName] = useState("");
  const [signUpDOB, setSignUpDOB] = useState("");
  const [signUpError, setSignUpError] = useState("");
  const navigate = useNavigate();

  const emailRegex = /^[a-zA-z0-9.%$-+*]+@[a-zA-z0-9.%$-+*]+\.[a-zA-Z]{2,}$/;
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[a-zA-Z\d@$!%*?&]{8,}$/;
  const firstNameRegex = /^[a-zA-Z]+$/;
  const secondNameRegex = /^[a-zA-Z]+$/;
  const dobRegex = /^(0[1-9]|[12]\d|3[01])-(0[1-9]|1[0-2])-\d{4}$/;
  const usernameRegex = /^[a-zA-Z0-9]+$/;


  const handleSignUp = async (e) => {
    e.preventDefault();

    if(!emailRegex.test(signUpEmail)){
        setSignUpError("Invalid email format: email must be formated as JohnDoe@gmail.com");
        setTimeout(() => {
            setSignUpError("");
        }, 3000);
        return;
    }

    if(!passwordRegex.test(signUpPassword)){
        setSignUpError("Invalid password format: password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character");
        setTimeout(() => {
            setSignUpError("");
        }, 3000);
        return;
    }

    if(signUpPassword !== signUpConfirmPassword){
        setSignUpError("Passwords do not match");
        setTimeout(() => {
            setSignUpError("");
        }, 3000);
        return;
    }
    
    if(!firstNameRegex.test(signUpFirstName)){
        setSignUpError("Invalid first name format: first name must only contain letters");
        setTimeout(() => {
            setSignUpError("");
        }, 3000);
        return;
    }

    if(!secondNameRegex.test(signUpSecondName)){
        setSignUpError("Invalid second name format: second name must only contain letters");
        setTimeout(() => {
            setSignUpError("");
        }, 3000);
        return;
    }
    
    if(!dobRegex.test(signUpDOB)){
        setSignUpError("Invalid date of birth format: date of birth must be formated as DD-MM-YYYY");
        setTimeout(() => {
            setSignUpError("");
        }, 3000);
        return;
    }

    if(!isValidDOB(signUpDOB)){
        setSignUpError("Please enter Valid DOB");
        setTimeout(() => {
            setSignUpError("");
        }, 3000);
        return;
    }

    try{
           const userCredential = await createUserWithEmailAndPassword(auth, signUpEmail, signUpPassword);
      const firebaseUid = userCredential.user.uid;
      const idToken = await userCredential.user.getIdToken();

      const response = await fetch("http://localhost:8080/api/user/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${idToken}`
        },
        body: JSON.stringify({
          username: firebaseUid,
          firstName: signUpFirstName,
          secondName: signUpSecondName,
          DOB: signUpDOB,
          email: signUpEmail
        })
      });

      if (!response.ok) throw new Error("Failed to create user profile");
      navigate("/dashboard");
    }catch(err){
        setSignUpError("Sign Up Failed "+ err.message);
    }
  }

  const isValidDOB = (dob) => {
    const [day, month, year] = dob.split("-").map(Number);
    const date = new Date(year, month - 1, day);
    return (
      date.getFullYear() === year &&
      date.getMonth() === month - 1 &&
      date.getDate() === day
    );
  };


  const handleLogin = async (e) => {
    e.preventDefault();

    if(!emailRegex.test(loginEmail)){
        setLoginError("Invalid email format: email must be formated as JohnDoe@gmail.com");
        setTimeout(() => {
            setLoginError("");
        }, 3000);
        return;
    }


    if(!passwordRegex.test(loginPassword)){
        setLoginError("Invalid password format: password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character");
        setTimeout(() => {
            setLoginError("");
        }, 3000);
        return;
    }

    try{
        const userCredential = await signInWithEmailAndPassword(auth, loginEmail, loginPassword);
        const user = userCredential.user;
        console.log("User logged in:", user);
        navigate("/Dashboard");


    }catch(error){
        console.error("Error logging in:", error);
        setLoginError("Invalid email or password");
        setTimeout(() => {
            setLoginError("");
        }, 3000);
    }

};
return(
    <div>
        <div>
        <h1>Simple Events</h1>
        <form onSubmit={handleLogin}>
            <input type="email" placeholder="Email" value={loginEmail} onChange={(e) => setLoginEmail(e.target.value)} required />
            <input type="password" placeholder="Password" value={loginPassword} onChange={(e) => setLoginPassword(e.target.value)} required />
            <button type="submit">Login</button>
        </form>
        {loginError && <p>{loginError}</p>}
    </div>
    <div>
        <h2>Sign Up</h2>
        <form onSubmit={handleSignUp}>
            <input type="email" placeholder="Email" value={signUpEmail} onChange={(e) => setSignUpEmail(e.target.value)} required />
            <input type="password" placeholder="Password" value={signUpPassword} onChange={(e) => setSignUpPassword(e.target.value)} required />
            <input type="password" placeholder="Confirm Password" value={signUpConfirmPassword} onChange={(e) => setSignUpConfirmPassword(e.target.value)} required />
            <input type="text" placeholder="First Name" value={signUpFirstName} onChange={(e) => setSignUpFirstName(e.target.value)} required />
            <input type="text" placeholder="Second Name" value={signUpSecondName} onChange={(e) => setSignUpSecondName(e.target.value)} required />
            <input type="text" placeholder="Date of Birth (DD-MM-YYYY)" value={signUpDOB} onChange={(e) => setSignUpDOB(e.target.value)} required />
            <button type="submit">Sign Up</button>
        </form>
        {signUpError && <p>{signUpError}</p>}
    </div>
    </div>    
    );
};

export default Login;

