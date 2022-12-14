import { Routes, Route } from 'react-router-dom';
import './App.css';
import CreateAccount from './components/CreateAccount';
import LandingPage from './components/landingpage';
import SignedInPage from './components/signedInPage';

function App() {
  return (
    <div className="appContainer">
        <div className='container page-container'>
          <div className='content-wrap'>
            <Routes>
              <Route path='/login' element={<LandingPage />} />
              <Route path="/create_profile" element={<CreateAccount />} />
              <Route path="/signedIn" element={<SignedInPage />} />
            </Routes>
          </div>
        </div>
    </div>
  );
}

export default App;
