import { useContext, useState } from "react";
import { Configuration } from '../App';
import Footer from "./pageElements/Footer";
import Header from "./pageElements/Header";
import axios from "axios";
const Worklist = () => {
    const configuration = useContext(Configuration);
    const {errorMsg, setErrorMsg} = useState("");

    const handleLogout = () => {
        axios.get('/ib/login/logout',{
        },
        { 
            headers: {
                'Authorization' : configuration.basicAuth,
                'X-XSRF-TOKEN': configuration.csrf,
                'Content-Type':'application/json'
            }
        }
        )
        .then((r) =>{
            configuration.setCurrentView("login");
        })
        .catch( (error ) => {
            setErrorMsg("Problem logging out");
        });
    }

    return (
        <>
            <Header/>
            <div className="spacer"></div>
            <form className="workDialog">
                <h2 className="dialogHeading">Working List</h2>
                {errorMsg && <p className="error">{errorMsg}</p>}
                
                
                <button type="button" onClick={handleLogout}>Logout</button>
            </form>
            <Footer/>
        </>
    )
};

export default Worklist;

