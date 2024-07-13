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
    const [confirmPass, setConfirmPass] = useState('');
    const [passStyle, setPassStyle] = useState('color:blue');

    const configuration = useContext(Configuration);
    const sendRegistration = async () => {
        console.log("sending registration with " + configuration.csrf);
        if (!userName || !password || !emailAddr) {
            setErrorMsg('Missing details.  Please fill in all fields');
            return;
        }
        if (confirmPass !== password) {
            setErrorMsg('Passwords do not match');
            setPassStyle("color:red");
            return;
        }
        axios.post('/ib/registration', {
                userName : userName,
                password : password,
                emailAddr: emailAddr
            },
            { headers: {
                'X-XSRF-TOKEN': configuration.csrf,
                "Content-Type":'application/json'
                }
            }
        )
        .then((r) =>{
            console.log('success ', r);
            configuration.setCurrentView('login');
    
        })
        .catch((error) => {
            console.log('registration', error.response ? error.response.data : error.msg);
            setErrorMsg(error.msg);
        });
    }

    return (
        <>
            <Header />
            <div className="spacer"></div>
            <div className="loginDialog">
                <h2 className="dialogHeading">New Login Registration</h2>

                <div>
                    {errorMsg && <p className="error">{errorMsg}</p>}
                    <div className="dialogLine">
                        <p><span className="dialogLabel">UserName:</span>
                            <input type="text" id='userName' name='userName' value={userName}
                                onChange={(e) => setUserName(e.target.value)} />
                        </p>
                    </div>
                    <div className="dialogLine">
                        <p><span className="dialogLabel">Password:</span>
                            <input type="text" id='password' value={password}
                                name='password'
                                onChange={(e) => setPassword(e.target.value)} />
                        </p>
                    </div>
                    <div className="dialogLine">
                        <p><span className="dialogLabel">Confirm Password:</span>
                            <input type="text" id='confirmPass' style={{passStyle}}
                                name='confirmPass'
                                onChange={(e) => setConfirmPass(e.target.value)} />
                        </p>
                    </div>
                    <div className="dialogLine">
                        <p><span className="dialogLabel">Email Address:</span>
                            <input type="text" id="emailAddr" value={emailAddr}
                                name='emailAddr'
                                onChange={(e) => setEmailAddr(e.target.value)} />
                        </p>
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
            </div>
            <Footer />
        </>);
};

export default Registration;