import { Routes, Route } from 'react-router-dom';
import './App.css';
import CreateAccount from './components/CreateAccount';
import LandingPage from './components/landingpage';
import SignedInPage from './components/signedInPage';
import InventoryView from './components/Inventory/InventoryView';
import InventoryAdd from './components/Inventory/InventoryAdd';
import InventoryUpdate from './components/Inventory/InventoryUpdate';
import InventoryDelete from './components/Inventory/InventoryDelete';

import EmployeeView from './components/Employee/EmployeeView';
import EmployeeAdd from './components/Employee/EmployeeAdd';
import EmployeeDelete from './components/Employee/EmployeeDelete';
import EmployeeUpdate from './components/Employee/EmployeeUpdate';

function App() {
  return (
    <div className="Appcontainer">
      <Routes>
        <Route path='/' element={<LandingPage />} exact/>
        <Route path="/create_profile" element={<CreateAccount />} />
        <Route path='/logged_in/*' element={<SignedInPage />} />

        <Route path='/inventory/view' element={<InventoryView />} />
        <Route path='/inventory/add' element={<InventoryAdd />} />
        <Route path='/inventory/delete' element={<InventoryDelete />} />
        <Route path='/inventory/update' element={<InventoryUpdate />} />

        <Route path='/employee/view' element={<EmployeeView />} />
        <Route path='/employee/add' element={<EmployeeAdd />} />
        <Route path='/employee/delete' element={<EmployeeDelete />} />
        <Route path='/employee/update' element={<EmployeeUpdate />} />
      </Routes>
    </div>
  );
}

export default App;