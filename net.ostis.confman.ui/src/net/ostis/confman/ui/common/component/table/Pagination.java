package net.ostis.confman.ui.common.component.table;

import java.util.List;


public class Pagination {
    
    private static final int STANDARD_COUNT_PER_PAGE = 15;

    private List<?> data;
    
    private int itemsPerPage;
    
    private int currentPage;
    
    public Pagination(List<?> data) {

        this(data, STANDARD_COUNT_PER_PAGE);
    }
    
    public Pagination(List<?> data, int itemsPerPage) {

        super();
        this.data = data;
        this.itemsPerPage = itemsPerPage;
    }
    
    public boolean hasPreviousPage() {
        
        return currentPage <= 1 ? false : true;
    }
    
    public boolean hasNextPage() {
        
        return currentPage >= getCountOfPages() ? false : true;
    }
    
    public List<?> getPreviousPage() {
        if(hasPreviousPage()) {
            currentPage--;
            List<?> page = data.subList(calcFirstIndex(), calcLastIndex());
            return page;
        }
        throw new IllegalArgumentException();
    }

    public List<?> getNextPage() {
        if(hasNextPage()) {
            currentPage++;
            List<?> page = data.subList(calcFirstIndex(), calcLastIndex());
            return page;
        }
        throw new IllegalArgumentException();
    }

    private int calcFirstIndex() {

        return (currentPage - 1) * itemsPerPage;        // discrepancy between array index and real value
    }
    
    private int calcLastIndex() {

        if(getCountOfPages() == currentPage) {
            return getItemCount();
        }
        return currentPage * itemsPerPage;      // discrepancy between array index and real value
    }
    
    public int getItemCount() {
        return data.size();
    }
    
    public int getCountOfPages() {
        return (int) Math.ceil(((double) getItemCount()) / itemsPerPage);
    }

    public int getCurrentPage() {

        return currentPage;
    }

    public List<?> getFirstPage() {

        currentPage = 0;
        return getNextPage();
    }

    public boolean tryToPage(int pageNumber) {
        if (pageNumber >= 1 && pageNumber <= getCountOfPages()) {
            currentPage = pageNumber;
            return true;
        }
        return false;
    }

    public List<?> getPage() {

        return data.subList(calcFirstIndex(), calcLastIndex());
    }
}
