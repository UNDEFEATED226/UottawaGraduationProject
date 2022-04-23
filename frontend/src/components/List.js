import "./List.css"
import Button from "./Button";
import { useNavigate } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useEffect, useState } from "react";

const List = ({items, columns, columnTitles, addButtonText, fixedUrl}) => {

    const { t } = useTranslation();
    const navigate = useNavigate();
    const [sortedItems, setSortedItems] = useState();
    const [sort, setSort] = useState({ column: null, order: null });

    useEffect(() => {
        setSortedItems(items);
    }, [items]);

    useEffect(() => {
        if (sort.column === null || sort.order === null) {
            setSortedItems(items);
            return;
        }
        setSortedItems([...items].sort((a, b) => {
            if (sort.order === 'ascending') {
                return a[sort.column] > b[sort.column] ? 1 : -1;
            }
            else if (sort.order === 'descending') {
                return a[sort.column] < b[sort.column] ? 1 : -1;
            }
            else return 0;
        }))
    }, [sort, items]);

    const handleSort = column => {
        let newOrder = null;
        if (sort.order === null || sort.column !== column)
            newOrder = 'ascending';
        else if (sort.order === 'ascending')
            newOrder = 'descending';
        else if (sort.order === 'descending')
            newOrder = null;
        setSort({ column: column, order: newOrder });
    }

    return (
        <div className="List">
            <table>
                <thead>
                    <tr className="ListHeader">
                        {columns.map(column => (
                            <th key={column}>
                                <div
                                    className={
                                        "columnTitle " +
                                        (sort.column === column ? (sort.order ?? '') : '')
                                    }
                                    onClick={() => handleSort(column)}
                                >
                                    {columnTitles[column]}
                                </div>
                            </th>
                        ))}
                        <th className="btnColTitle"></th>
                    </tr>
                </thead>
                <tbody>
                    {sortedItems?.map(item => (
                        <tr className="ListItem" key={item.id}>
                            {columns.map(column => (
                                <td key={column}>{item[column]}</td>
                            ))}
                            <td>
                                <Button
                                    text={t('button.edit')}
                                    clickHandler={() => navigate(`/${fixedUrl}/${item.id}`)}
                                />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Button
                text={addButtonText ?? t('button.add')}
                clickHandler={() => navigate(`/${fixedUrl}/new`)}
            />
        </div>
    );
}

export default List;
