import './css/App.css';
import {useState, createContext, useEffect} from 'react';
import Login from './components/Login';
import Registration from './components/Registration';
import HomePage from './components/HomePage.js';
import ForgotPassword from './components/ForgotPassword';
import axios from 'axios';

export const Configuration = createContext({});

const App = () => {
  const [csrfToken, setCsrfToken] = useState('');
  const [currentView, setCurrentView] = useState('login');
  const [basicAuth, setBasicAuth] = useState('');
  useEffect( () => {
    if (csrfToken === '') {
     
      axios.get('/config')
        .then((response) => { 
          setCsrfToken(response.data.token.token);
      });
    }
  }, [csrfToken]);

  return (
  <>
    <Configuration.Provider value={{
        'csrf':csrfToken, 
        'basicAuth': basicAuth,
        'setCurrentView' : setCurrentView,
        'setBasicAuth' : setBasicAuth}}>
      {currentView === 'login' && <Login/>}
      {currentView === 'registration' && <Registration/>}
      {currentView === 'homepage' && <HomePage/>}
      {currentView === 'forgotPassword' && <ForgotPassword/>} 
    </Configuration.Provider>
  </>
)};


export default App;

