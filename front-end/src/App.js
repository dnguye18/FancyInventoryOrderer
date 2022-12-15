import { Routes, Route } from 'react-router-dom';
import './App.css';
import CreateAccount from './components/CreateAccount';
import LandingPage from './components/landingpage';
import SignedInPage from './components/signedInPage';
import InventoryView from './components/Inventory/InventoryView';

function App() {
  return (
    <div className="Appcontainer">
      
      <Routes>
        <Route path='/' element={<LandingPage />} />
        <Route path="/create_profile" element={<CreateAccount />} />
        <Route path='/logged_in' element={<SignedInPage />} />
        <Route path='/inventory/view' element={<InventoryView />} />
      </Routes>
    </div>
  );
}

export default App;
