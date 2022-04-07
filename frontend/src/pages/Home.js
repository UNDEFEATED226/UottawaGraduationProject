import './Home.css'
import Button from "components/Button";
import { useNavigate } from "react-router-dom";

const Home = () => {

    const navigate = useNavigate();

    return (
        <div className="Home">
            <h1>Home page</h1>
            <Button
                text="Log In"
                clickHandler={() => navigate('/login')}
            />
            <Button
                text="Edit my info"
                clickHandler={() => navigate('/basic_info')}
            />
        </div>
    );
}
 
export default Home;
