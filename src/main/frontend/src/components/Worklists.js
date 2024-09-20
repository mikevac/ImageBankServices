import axios from "axios";
import {useEffect, useState} from 'react';

/*
render the contents of a worklist entry
*/
const WorkListEntry = (entry) => {
    return (
    <>
        <div id={entry.id}>{entry.name}</div>
    </>
    );
}

/*
display a single work list entry

*/
const WorkingList = (worklist) => {
    return (
    <>
        <div className="listEntry">
            if (worklist.length === 0) return (<div>No lists available...</div>);
            {worklist.map( (entry) => {
                return (
                    <WorkListEntry worklist={entry} />
                );
            })}
        </div>
    </>
    );
};

/*
display a list of worklists 
*/
const WorkLists = () => {
    const [worklist, setWorklist] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect( () => {
        async function getDocList(){
            setLoading(true);
            const results = await axios.get("worklist");
            setWorklist(results.data);
            setLoading(false);
        }
        getDocList();
    }, []);

    if (loading) return <div>Loading...</div>

    return (
        <div className="listContainer">
            <WorkingList worklists={worklist}/>
        </div>
    )
};

export default WorkLists;