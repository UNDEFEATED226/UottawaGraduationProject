import './Login.css';
import Textbox from "components/Textbox";
import Button from 'components/Button';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {

    const navigate = useNavigate();

    const [loginInfo, setLoginInfo] = useState({email: '', password: ''});
    const [loginFailed, setLoginFailed] = useState(false);

    const handleFieldChange = (id, value) => {
        setLoginInfo({ ...loginInfo, [id]: value });
    }

    async function doLogin() {
        const response = await fetch('/api/login', {
            method: 'POST',
            body: new URLSearchParams(loginInfo)
        });
        if (response.status === 200) {
            navigate("/basic_info");
        }
        else if (response.status === 401) {
            setLoginFailed(true);
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        doLogin();
    }

    return (
        <div className="Login">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <Textbox
                    name="email"
                    labelText="Email:"
                    required
                    onChange={handleFieldChange}
                />
                <Textbox
                    name="password"
                    labelText="Password:"
                    required
                    onChange={handleFieldChange}
                />
                <Button
                    text="Log In"
                    type={1}
                    htmlButtonType="submit"
                />
            </form>
            {loginFailed && <div className="loginError">Login failed. Please check your credentials.</div>}
        </div>
    );
}

export default Login;
