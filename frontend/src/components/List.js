import "./List.css"

const List = ({items, columns}) => {
    return (
        <div className="List">
            <table>
                <thead>
                    <tr className="ListHeader">
                        {columns.map(column => (
                            <th key={column}>{column}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {items.map(item => (
                        <tr className="ListItem" key={item.id}>
                            {columns.map(column => (
                                <td key={column}>{item[column]}</td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
 
export default List;