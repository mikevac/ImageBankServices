import axios from "axios";
import {useEffect, useState} from 'react';


const WorkingList = (documents) => {
    <>
        <div className="listEntry">
            {documents.map( (doc) => {
                return (
                    <DocumentTimeline
                        key={doc.docId}

                )
            })}

        </div>
    </>

};

const DocumentList = () => {
    const [documents, setDocuments] = useState([]);
    const [loading, setLoading] = useState(true);
    useEffect( () => {
        async function getDocList(){
            setLoading(true);
            const results = await axios.get("/ib/worklist");
            setDocuments(results.data);
            setLoading(false);
        }
        getDocList();
    }, []);

    if (loading) return <div>Loading...</div>

    return (
        <div className="listContainer">
            <WorkingList documents={documents}/>
        </div>
    )
};

export default DocumentList;