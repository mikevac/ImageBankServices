import React, { useState, useContext } from 'react';
import axios from 'axios';
import { Configuration } from '../App';
import Header from './pageElements/Header';
import Footer from './pageElements/Footer';

const Registration = () => {
    const [errorMsg, setErrorMsg] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [emailAddr, setEmailAddr] = useState('');
    const [passStyle, setPassStyle] = useState('blue');
    const [passVisibility, setPassVisibility] = useState('false');

    const complexity = (password) => {
        if (password.length < 8){
            setErrorMsg("Passwords must be 8 or more characters in length");
            setPassStyle('red');
            return false;
        } 
        const cap = /[A-Z]/g;
        if (!password.match(cap)){
            setErrorMsg("Passwords must have at least one upper case letter");
            setPassStyle('red');
            return false;
        }
        const num = /[0-9]/g
        if (!password.match(num)){
            setErrorMsg("Passwords must have at least one number");
            setPassStyle('red');
            return false;
        }
        const special = /[!@#$%\\^&\\*\\.]/g
        if (!password.match(special)){
            setErrorMsg("Passwords must have at least one special character '!@#$%^&*'");
            setPassStyle('red');
            return false;
        }
        return true;
    }

    const configuration = useContext(Configuration);

    const onPassChange = (event) => {
        setPassword(event.target.value);
        setPassStyle('blue');
    }

    const togglePass = (event) => {
        setPassVisibility(!passVisibility);
        if (passVisibility){
            document.getElementById("password").type = "text";
            document.getElementById("passTog").textContent = 'X';
        } else {
            document.getElementById("password").type = "password";
            document.getElementById("passTog").textContent = 'O';
        }
    }

    const sendRegistration = async () => {
        console.log("in sendRegistration");
        if (!userName || !password || !emailAddr) {
            setErrorMsg('Missing details.  Please fill in all fields');
            return;
        }
        if (!complexity(password)){
            return;
        }
        axios.post('/registration', {
                userName : userName,
                password : password,
                emailAddr: emailAddr,
                _csrf : configuration.csrf
            },
            { headers: {
                "Content-Type":'application/json'
                }
            }
        )
        .then((r) =>{
            configuration.setCurrentView('login');    
        })
        .catch((error) => {
            setErrorMsg(error.msg);
        });
    }

    return (
        <>
            <Header />
            <div className="spacer"></div>
            <form className="loginDialog">
                <h2 className="dialogHeading">Registration</h2>

                <div>
                    <div className="dialogLine">
                        <p className="error" style={{color: "red"}}>{errorMsg}&nbsp;</p>
                    </div>
                    <div className="dialogLine">
                        <p>
                            <span className="dialogLabel">User Name:</span>
                            <input type="text" id='userName' name='userName' value={userName}
                                autoComplete='username'
                                onChange={(e) => setUserName(e.target.value)} />
                        </p>
                    </div>
                    <div className="dialogLine">
                        <p>
                            <span className="dialogLabel">Email Address:</span>
                            <input type="email" id="emailAddr" value={emailAddr}
                                name='emailAddr'
                                onChange={(e) => setEmailAddr(e.target.value)} />
                        </p>
                    </div>
                    <div className="dialogLine">
                        <p>
                            <span className="dialogLabel" style={{color: passStyle}}>Password:</span>
                            <input type="password" id='password' value={password}
                                name='password' 
                                autoComplete='new-password' 
                                style={{color:passStyle}}
                                onChange={(e) => onPassChange(e)} />
                            <span id="passTog" style={{marginLeft: "-20px", cursor: "pointer"}} onClick={(e) => togglePass(e)}>O</span>
                        </p>
                    </div>
                    <div>
                        <p>&nbsp;</p>
                    </div>
                    <div>
                        <p>&nbsp;</p>
                    </div>
                    <div className="dialogButton">
                        <p className="inputLabel"></p>
                        <button type="button" onClick={sendRegistration}>Register</button>
                    </div>
                    <div>
                        <p>&nbsp;</p>
                    </div>
                </div>
            </form>
            <Footer />
        </>);
};

export default Registration;