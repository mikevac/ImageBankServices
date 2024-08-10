import Footer from "./pageElements/Footer";
import Header from "./pageElements/Header";
import { useState, useContext } from "react";
import { Configuration } from "../App";
import axios from "axios";
import { Buffer } from "buffer";


const Login = (setUser) => {
    const [errorMsg, setErrorMsg] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const configuration = useContext(Configuration);

    // const getAuth = () => {
    //     return 'Basic '+ Buffer.from(userName + ':' + password).toString('base64');
    // }

    const handleLogin = async() => {
        if (!userName || !password){
            setErrorMsg("Please enter the a name and password");
            return;
        }
        console.log("csrf token =", configuration);
        console.log('Basic ' + Buffer.from(userName + ':' + password).toString('base64'));
        const auth = 'Basic '+ Buffer.from(userName + ':' + password).toString('base64');
        console.log('auth is ' + auth);
        axios.post('/ib/login',{
            userName,
            password,
            '_csrf' : configuration.csrf,
        },
        { 
            headers: {
                'Authorization' : 'Basic '+ Buffer.from(userName + ':' + password).toString('base64'),
                'X-XSRF-TOKEN': configuration.csrf,
                'Content-Type':'application/json'
            }
        }
        )
        .then((r) =>{
            console.log('success ', r);
            configuration.setCurrentView('worklist');
    
        })
        .catch( (error ) => {
            console.log('login', error.response ? error.response.data : error.msg);
            setErrorMsg(error.msg);
        });
    };
    const handleForgotPassword = () => {
        configuration.setCurrentView('forgotPassword');
    }

    const handleRegistration = () => {
        configuration.setCurrentView('registration');
    }
    return (
    <>
        <Header />
        <div className="spacer"></div>
        <div className="loginDialog">
            <h2 className="dialogHeading">ImageBank Login</h2>
            <div>
                <div className="inputLine">
                    <p className="inputLabel">Name/Email:</p>
                    <input className="inputControl" type="text" name="userName" value={userName}
                        onChange={(e) => setUserName(e.target.value)}/>
                </div>
                <div className="inputLine">
                    <p className="inputLabel">Password:</p>
                    <input className="inputControl" type="password" name="password" value={password}
                        onChange={(e) => setPassword(e.target.value)} />
                </div>
                <div>
                    {errorMsg && <p className="error">{errorMsg}</p>}
                </div>
                <div className="dialogButton">
                    <p className="inputLabel"></p>
                    <button type="button" onClick={handleLogin}>Login</button>
                </div>
            </div>
            <div className="dialogLinks">
                <p>&nbsp;</p>
                <p className="buttonLink" onClick={handleForgotPassword}>Forgot password</p>
                <p className="buttonLink" onClick={handleRegistration}>Register A New Account</p>
            </div>
        </div>
        <Footer />
    </>
)};

export default Login;