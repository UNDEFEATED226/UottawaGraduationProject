import './Login.css';
import lifeLogo from 'assets/life-logo-g.svg';
import uoLogo from 'assets/uottawa-horizontal-logo.svg';
import Textbox from "components/Textbox";
import Button from 'components/Button';
import { useTranslation } from 'react-i18next';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {

    const { t } = useTranslation();
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
        if (response.ok) {
            navigate("/basic_info");
        }
        else {
            setLoginFailed(true);
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        doLogin();
    }

    return (
        <div className="Login">
            <div className="logoContainer">
                <img className="lifeLogo" src={lifeLogo} alt={t('header.logo_alt')} />
                <img className="uoLogo" src={uoLogo} alt={t('header.logo_alt')} />
            </div>
            <div className="loginFormContainer">
                <h2>Member Login</h2>
                <form onSubmit={handleSubmit}>
                    <Textbox
                        name="email"
                        labelText="Email:"
                        text={loginInfo.email}
                        required
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name="password"
                        labelText="Password:"
                        text={loginInfo.password}
                        isPassword
                        required
                        onChange={handleFieldChange}
                    />
                    <Button
                        text="Log in"
                        htmlButtonType="submit"
                    />
                </form>
                <div className="loginError">
                    {loginFailed && "Login failed. Please check your credentials."}
                </div>
            </div>
        </div>
    );
}

export default Login;
