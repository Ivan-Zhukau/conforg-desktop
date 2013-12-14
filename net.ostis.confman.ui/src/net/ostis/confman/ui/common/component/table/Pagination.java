package net.ostis.confman.ui.common.component.table;

import java.util.List;

public class Pagination {

    private static final int STANDARD_COUNT_PER_PAGE = 15;

    private List<?>          data;

    private int              itemsPerPage;

    private int              currentPage;

    public Pagination(final List<?> data) {

        this(data, STANDARD_COUNT_PER_PAGE);
    }

    public Pagination(final List<?> data, final int itemsPerPage) {

        super();
        this.data = data;
        this.itemsPerPage = itemsPerPage;
    }

    public boolean hasPreviousPage() {

        return this.currentPage <= 1 ? false : true;
    }

    public boolean hasNextPage() {

        return this.currentPage >= getCountOfPages() ? false : true;
    }

    public List<?> getPreviousPage() {

        if (hasPreviousPage()) {
            this.currentPage--;
            final List<?> page = this.data.subList(calcFirstIndex(),
                    calcLastIndex());
            return page;
        }
        throw new IllegalArgumentException();
    }

    public List<?> getNextPage() {

        if (hasNextPage()) {
            this.currentPage++;
            final List<?> page = this.data.subList(calcFirstIndex(),
                    calcLastIndex());
            return page;
        }
        throw new IllegalArgumentException();
    }

    private int calcFirstIndex() {

        return (this.currentPage - 1) * this.itemsPerPage; // discrepancy
                                                           // between array
                                                           // index and real
                                                           // value
    }

    private int calcLastIndex() {

        if (getCountOfPages() == this.currentPage) {
            return getItemCount();
        }
        return this.currentPage * this.itemsPerPage; // discrepancy between
                                                     // array index and real
                                                     // value
    }

    public int getItemCount() {

        return this.data.size();
    }

    public int getCountOfPages() {

        return (int) Math.ceil(((double) getItemCount()) / this.itemsPerPage);
    }

    public int getCurrentPage() {

        return this.currentPage;
    }

    public List<?> getFirstPage() {

        this.currentPage = 0;
        return getNextPage();
    }

    public boolean tryToPage(final int pageNumber) {

        if (pageNumber >= 1 && pageNumber <= getCountOfPages()) {
            this.currentPage = pageNumber;
            return true;
        }
        return false;
    }

    public List<?> getPage() {

        return this.data.subList(calcFirstIndex(), calcLastIndex());
    }

    public int getItemsPerPage() {

        return this.itemsPerPage;
    }

    public void setItemsPerPage(final int itemsPerPage) {

        this.itemsPerPage = itemsPerPage;
    }
}
