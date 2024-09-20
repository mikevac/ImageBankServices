import React from "react";
import Header from "./pageElements/Header";
import Footer from "./pageElements/Footer";
import Worklists from "./Worklists";
import Albums from "./Albums";
import Thumbnails from "./Thumbnails";

const HomePage = () => {
    return (
        <>
            <Header />
            <div>
                <div><Worklists /></div>
                <div><Albums /></div>
                <div><Thumbnails /></div>
            </div>
            <Footer />
        </>
    );
}

export default HomePage;