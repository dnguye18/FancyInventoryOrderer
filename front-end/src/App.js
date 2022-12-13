import { Routes, Route } from 'react-router-dom';
import './App.css';
import CreateAccount from './components/CreateAccount';
import LandingPage from './components/landingpage';

function App() {
  return (
    <div className="Appcontainer">
      
      <Routes>
<<<<<<< HEAD
        <Route path="/login" element={<LandingPage />}/>
=======
        <Route path='/login' element={<LandingPage />} />
>>>>>>> front-end
        <Route path="/create_profile" element={<CreateAccount />} />
      </Routes>
    </div>
  );
}

export default App;
