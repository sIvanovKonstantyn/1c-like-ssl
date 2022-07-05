import { PaginationOptions } from 'react-bootstrap-table-next';

export function paginationFactory():  { options?:PaginationOptions} {
    return {
        options: {
            page: 1,
            sizePerPage: 50,
            lastPageText: '>>',
            firstPageText: '<<',
            nextPageText: '>',
            prePageText: '<',
            showTotal: true,
            alwaysShowAllBtns: true,
            onPageChange(page, sizePerPage) { },
            onSizePerPageChange(page, sizePerPage) {

            }
        }
    }
}