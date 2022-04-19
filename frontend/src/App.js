import './App.css'
import { useCallback, useEffect, useState } from 'react';
import { Outlet } from 'react-router-dom';
import Header from 'components/Header';
import SideBar from 'components/SideBar';
import Notification from 'components/Notification';

const App = () => {

    const [notifications, setNotifications] = useState([]);

    // Clears notifications in 3 seconds if there are new ones
    useEffect(() => {
        if (notifications.length === 0) return;
        let timeoutID = setTimeout(() => {
            setNotifications(n => n.slice(1));
        }, 3000);
        return () => clearTimeout(timeoutID);
    }, [notifications]);

    // Type must be 'positive', 'negative', or 'info'
    const pushNotification = useCallback((type, message) => {
        setNotifications(n => [...n, {
            id: Date.now(),
            type: type,
            text: message
        }]);
    }, []);

    return (
        <div className="App">
            <Header username="Shize Li" />
            <SideBar defaultSelect="BasicInfo" />
            <Outlet context={{ pushNotification }} />
            <Notification notifications={notifications} />
        </div>
    );
}

export default App;
