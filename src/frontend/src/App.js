import './css/App.css';
import {useState, createContext, useEffect} from 'react';
import Login from './components/Login';
import Registration from './components/Registration';
import Worklist from './components/Worklist.js';
import ForgotPassword from './components/ForgotPassword';
import axios from 'axios';

export const Configuration = createContext({});

const App = () => {
  const [urlBase, setUrlBase] = useState('https://localhost:8080/ib/');
  const [csrfToken, setCsrfToken] = useState('');
  const [currentView, setCurrentView] = useState('login');
  const [basicAuth, setBasicAuth] = useState('');
  useEffect( () => {
    if (csrfToken === '') {
      axios.get('/ib/config')
        .then((response) => { 
          setUrlBase(response.data.baseUrl + response.data.context);
          setCsrfToken(response.data.token.token);
          console.log("csrf = " + response.data.token.token);
      });
    }
  }, [csrfToken]);

  return (
  <>
    <Configuration.Provider value={{
        'url': urlBase, 
        'csrf':csrfToken, 
        'setCurrentView' : setCurrentView,
        'setBasicAuth' : setBasicAuth}}>
      {currentView === 'login' && <Login/>}
      {currentView === 'registration' && <Registration/>}
      {currentView === 'worklist' && <Worklist/>}
      {currentView === 'forgotPassword' && <ForgotPassword/>} 
    </Configuration.Provider>
  </>
)};


export default App;

