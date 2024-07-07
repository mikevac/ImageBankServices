import './css/App.css';
import {useState, createContext, useEffect} from 'react';
import Login from './components/Login';
import Registration from './components/Registration';
import Worklist from './components/Worklist.js';
import ForgotPassword from './components/ForgotPassword';
import axios from 'axios';

export const Configuration = createContext({});

const App = () => {
  const [urlBase, setUrlBase] = useState('https://localhost:8080/');
  const [csrfToken, setCsrfToken] = useState('');
  const [currentView, setCurrentView] = useState('login');
  useEffect( () => {
    axios.get('http://localhost:8080/config')
      .then((response) => { 
        setUrlBase(response.data.baseUrl + response.data.context);
        setCsrfToken(response.data.token.token);
    });
  }, []);

  return (
  <>
    {console.log('currentView is ' + currentView)}
    <Configuration.Provider value={{"url": urlBase, "csrf":csrfToken, "setCurrentView" : setCurrentView}}>
      {currentView === 'login' && <Login/>}
      {currentView === 'registration' && <Registration/>}
      {currentView === 'worklist' && <Worklist/>}
      {currentView === 'forgotPassword' && <ForgotPassword/>} 
    </Configuration.Provider>
  </>
)};


export default App;

