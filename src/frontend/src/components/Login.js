import Footer from "./pageElements/Footer";
import Header from "./pageElements/Header";
import { useState, useContext } from "react";
import { Configuration } from "../App";
import axios from "axios";

const Login = (setUser) => {
    const [errorMsg, setErrorMsg] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const configuration = useContext(Configuration);

    const handleLogin = async() => {
        try {
            if (userName || password){
                setErrorMsg("Invalid username/password combintation");
                return;
            }
            const response = axios.post(configuration.url + '/ib/login',{
                userName,
                password,
                "_csrf":configuration.csrfToken
            }) ;
            console.log(response);
            return;
        } catch( error ){
            console.log('registration', error.response ? error.response.data : error.msg);
            setErrorMsg(error.msg);
        }
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
            <form action="/login/signin" method="post">
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
                    <button type="submit" onClick={handleLogin}>Login</button>
                </div>
            </form>
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