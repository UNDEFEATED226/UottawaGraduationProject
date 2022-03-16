import "./List.css"
import Button from "./Button";
import { Link, useNavigate } from 'react-router-dom';
import { useTranslation } from 'react-i18next';

const List = ({items, columns, fixedUrl}) => {
    const { t } = useTranslation();
    const navigate = useNavigate();

    return (
        <div className="List">
            <table>
                <thead>
                    <tr className="ListHeader">
                        {columns.map(column => (
                            <th key={column}>{column}</th>
                        ))}
                        <th key={"buttons"}></th>
                    </tr>
                </thead>
                <tbody>
                    {items.map(item => (
                        <tr className="ListItem" key={item.id}>
                            {columns.map(column => (
                                <td key={column}>{item[column]}</td>
                            ))}
                            <td key={item.id + 'button'}>
                                <Button text={t('button.edit')} clickHandler={() => navigate(`/${fixedUrl}/${item.id}`)}/>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
 
export default List;