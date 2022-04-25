import './App.css'
import Header from 'components/Header';
import SideBar from 'components/SideBar';
import Notification from 'components/Notification';

import { useCallback, useEffect, useState } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';

import { getMember } from 'api/members';

const App = () => {

    const navigate = useNavigate();

    const [notifications, setNotifications] = useState([]);
    const [member, setMember] = useState({});

    const fetchData = useCallback(async () => {
        const data = await getMember();
        if (data == null) navigate('/login');
        else setMember(data);
    }, [navigate])

    useEffect(() => {
        fetchData();
    }, [fetchData]);

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
            <Header username={`${member.firstName} ${member.lastName}`} />
            <SideBar />
            <Outlet context={{ pushNotification }} />
            <Notification notifications={notifications} />
        </div>
    );
}

export default App;
