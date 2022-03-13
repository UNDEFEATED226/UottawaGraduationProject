import './SideBar.css'
import { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useTranslation } from 'react-i18next';

const SideBar = ({ defaultSelect }) => {

    const [collapsed, setCollapsed] = useState(false);
    const [selected, setSelected] = useState(defaultSelect);
    const location = useLocation();
    const { t } = useTranslation();

    useEffect(() => {
        if (location.pathname === '/') setSelected("BasicInfo");
        else if (location.pathname === '/my_products') setSelected("MyProducts");
        else if (location.pathname === '/my_grants') setSelected("MyGrants");
        else if (location.pathname === '/my_events') setSelected("MyEvents");
        else if (location.pathname === '/my_supervisions') setSelected("MySupervisions");
        else if (location.pathname === '/partners') setSelected("Partners");
    }, [location])

    return (
        <div className={"SideBar" + (collapsed? " collapsed" : "")}>
            <button id='collapse-btn' onClick={() => setCollapsed(!collapsed)}>
                {collapsed? ">" : ("< " + t('button.collapse'))}
            </button>
            <Link to='/' onClick={() => setSelected('BasicInfo')}>
                <div className={selected === "BasicInfo"? "selected" : ""}>
                    {t('page_titles.basic_information')}
                </div>
            </Link>
            <Link to='/my_products' onClick={() => setSelected('MyProducts')}>
                <div className={selected === "MyProducts"? "selected" : ""}>
                    {t('page_titles.my_products')}
                </div>
            </Link>
            <Link to='/my_grants' onClick={() => setSelected('MyGrants')}>
                <div className={selected === "MyGrants"? "selected" : ""}>
                    {t('page_titles.my_grants')}
                </div>
            </Link>
            <Link to='/my_events' onClick={() => setSelected('MyEvents')}>
                <div className={selected === "MyEvents"? "selected" : ""}>
                    {t('page_titles.my_events')}
                </div>
            </Link>
            <Link to='/my_supervisions' onClick={() => setSelected('MySupervisions')}>
                <div className={selected === "MySupervisions"? "selected" : ""}>
                    {t('page_titles.my_supervision')}
                </div>
            </Link>
            <Link to='/partners' onClick={() => setSelected('Partners')}>
                <div className={selected === "Partners"? "selected" : ""}>
                    {t('page_titles.partners')}
                </div>
            </Link>
            <div></div>
        </div>
    );
}

export default SideBar;
