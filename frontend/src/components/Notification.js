import './Notification.css'
import PropTypes from "prop-types";

const Notification = ({ notifications }) => {
    return (
        <div className="Notification">
            {notifications.map(n => (
                <div className={n.type} key={n.id}>{n.text}</div>
            ))}
        </div>
    );
}
Notification.propTypes = { notifications: PropTypes.array.isRequired }
export default Notification;
