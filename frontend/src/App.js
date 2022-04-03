import './App.css'
import { Outlet } from 'react-router-dom';
import Header from 'components/Header';
import SideBar from 'components/SideBar';

const App = () => {
    return (
        <div className="App">
            <Header username="Joe"/>
            <SideBar defaultSelect="BasicInfo"/>
            <Outlet />
        </div>
    );
}

export default App;
