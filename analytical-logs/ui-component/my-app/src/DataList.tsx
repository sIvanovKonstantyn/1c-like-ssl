import React, { useState, useEffect, useMemo } from "react";
import LogItem from "./LogItem"
import "bootstrap/dist/css/bootstrap.min.css"
import DataTable, { TableColumn } from "react-data-table-component"
import SortIcon from "@material-ui/icons/ArrowDownward";


function DataList() {

    function handleKeyDown(key: string) {
        if(key !== 'Enter') {
            return
        }
        takeDataFromServer()
    }
    function takeDataFromServer() {
        fetch("http://localhost:8080/logging?filterText=" + filterText)
            .then(response => response.json())
            .then(result => setlogList(result))
            .catch(e => console.log(e))
    }

    const [logList, setlogList] = useState<Array<LogItem>>([]);
    const [filterText, setFilterText] = useState('');

    const columns: TableColumn<LogItem>[] = [
        { selector: row => row.ts, name: 'Time', sortable: true },
        { selector: row => row.context.user, name: 'User' },
        { selector: row => row.context.ip, name: 'IP', sortable: true },
        { selector: row => row.context.clientType, name: 'Client type', sortable: true },
        { selector: row => row.context.traceId, name: 'Trace ID', sortable: true },
        { selector: row => row.context.object, name: 'Context', sortable: true },
        { selector: row => row.context.methodName, name: 'Operation', sortable: true },
        { selector: row => row.msg, name: 'Comment' }
    ];



    const subHeaderComponent = useMemo(() => {
        return (
            <input onChange={e => setFilterText(e.target.value)} onKeyDown={e => handleKeyDown(e.key)} />
        );
    }, [filterText]);

    useEffect(() => {
        takeDataFromServer()
    }, [])



    return <div>
        <DataTable
            columns={columns}
            data={logList}
            defaultSortFieldId="Time"
            sortIcon={<SortIcon />}
            pagination
            selectableRows
            subHeader
            subHeaderComponent={subHeaderComponent}
        />
    </div>
}

export default DataList;
