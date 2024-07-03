import './css/App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {useState, createContext} from 'react';
import Login from './components/Login';
import Registration from './components/Registration';
import Worklist from './components/Worklist.js';

export const Configuration = createContext({});

const App = () => {
  const [urlBase, setUrlBase] = useState('https://localhost:8080/imagebank');
  const [csrfToken, setCsrfToken] = useState('');

  fetch('hostfile.json',
    { headers: {
      'Content-Type': 'application/json',
      'Accept' : 'application/json'
      }
    }
  )
    .then((r) => r.json())
    .then((r) => {
      setUrlBase(r.data.host + r.data.context + "/");
      setCsrfToken(r.data._csrf);
    }
  );

  return (
  <>
    <Configuration.Provider value={{"url": urlBase, "csrf":csrfToken}}>
      <Router>
          <Routes>
            <Route path="/" element={<Login/>} />
            <Route path="/register" element={<Registration/>} />
            <Route path="/worklist" element={<Worklist/>} />
          </Routes>
      </Router>
    </Configuration.Provider>
  </>
)};


export default App;

