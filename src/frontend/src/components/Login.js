import Footer from "./pageElements/Footer";
import Header from "./pageElements/Header";

const Login = (setUser) => {

    
    const handleLogin = () => {

    };
    return (
    <>
        <Header />
        <div className="spacer"></div>
        <div className="loginDialog">
            <h2 className="dialogHeading">ImageBank Login</h2>
            <form action="/login/signin" method="post">
                <div className="inputLine">
                    <p className="inputLabel">Name/Email:</p>
                    <input className="inputControl" type="text" name="userName" />
                </div>
                <div className="inputLine">
                    <p className="inputLabel">Password:</p>
                    <input className="inputControl" type="password" name="password" />
                </div>
                <div className="dialogButton">
                    <p className="inputLabel"></p>
                    <button type="submit" onClick={handleLogin}>Login</button>
                </div>
            </form>
            <div className="dialogLinks">
                <p>&nbsp;</p>
                <p>
                    <a href="/forgot">Forgot password</a>
                </p>
                <p>
                    <a href="/register">Register</a>
                </p>
            </div>
        </div>
        <Footer />
    </>
)};

export default Login;