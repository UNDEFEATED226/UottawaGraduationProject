import './Notification.css'

const Notification = ({ notifications }) => {
    return (
        <div className="Notification">
            {notifications.map(n => (
                <div className={n.type} key={n.id}>{n.text}</div>
            ))}
        </div>
    );
}

export default Notification;
