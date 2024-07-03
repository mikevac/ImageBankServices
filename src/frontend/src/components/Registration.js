import React, {useState, useContext} from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import {Configuration} from '../App';

const Registration = () => {
    const [errorMsg, setErrorMsg] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [emailAddr, setEmailAddr] = useState('');
    const [confirmPass, setConfirmPass] = useState('');
    const [passStyle, setPassStyle] = useState('color:blue;');
    const history = useNavigate();

    const configuration = useContext(Configuration);
    const sendRegistration = async() => {
        try {
            if (!userName || !password || !emailAddr ){
                setErrorMsg('Missing details.  Please fill in all fields');
                return;
            }
            const response = axios.post( configuration.url + '/ib/registration',{
                userName,
                password,
                emailAddr,
                "_csrf":configuration.csrfToken
            });
            console.log(response);
            history('/dashboard');
        } catch( error ){
            console.log('registration', error.response ? error.response.data : error.msg);
            setErrorMsg(error.msg);
        }
    }

    const checkPassword = () => {
        confirmPass !== password ? setPassStyle('color:red;') :
            setPassStyle('color:blue');
    }

    <div>
        <h2 className="dialogHeading">New Login Registration</h2>
        {errorMsg && <p className="error">{errorMsg}</p>}
            <input type="text" id='userName' placeholder={"User Name"} value={userName}
                onChange={(e) => setUserName(e.target.value)}/>
            <input type="text" id='password' value={password}
                onChange={(e) => setPassword(e.target.value)}/>
            <input type="text" id='confirmPass' value='' style={passStyle}
                onChange={(e) => {setConfirmPass(e.target.value); checkPassword();}}/>
            <input type="text" id="emailAddr" value={emailAddr}
                onChange={(e) => setEmailAddr(e.target.value)}/>
        <button onClick={sendRegistration}>Register</button>
    </div>
};

export default Registration;