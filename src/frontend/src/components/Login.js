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

    const handleLogin = async() => {
        if (!userName || !password){
            setErrorMsg("Please enter the a name and password");
            return;
        }
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
            configuration.setBasicAuth('Basic '+ Buffer.from(userName + ':' + password).toString('base64'));
            configuration.setCurrentView('worklist');
        })
        .catch( (error ) => {
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
        <form className="loginDialog">
            <h2 className="dialogHeading">ImageBank Login</h2>
            <div>
                <div className="inputLine">
                    <p className="inputLabel">Name/Email:</p>
                    <input className="inputControl" type="text" name="userName" value={userName}
                        autoComplete="username"
                        onChange={(e) => setUserName(e.target.value)}/>
                </div>
                <div className="inputLine">
                    <p className="inputLabel">Password:</p>
                    <input className="inputControl" type="password" name="password" value={password}
                        autoComplete="current-password"
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
        </form>
        <Footer />
    </>
)};

export default Login;