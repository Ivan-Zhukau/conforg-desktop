package net.ostis.confman.ui.common.component.table;

import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PagingElement {

    private enum Captions implements Localizable {
        FIRST_BUTTON("pagingFirstBtn"),
        LAST_BUTTON("pagingLastBtn"),
        PREV_BUTTON("pagingPrevBtn"),
        NEXT_BUTTON("pagingNextBtn"),
        ITEMS_PER_PAGE("pagingItemsPerPage"),
        ITEM_COUNT("pagingItemCount"),
        PAGE("pagingPage"),
        OF("pagingOf");

        private String rk;

        private Captions(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private Pagination     pagination;

    private Label          labelCountPerPage;

    private Label          labelInfo;

    private DynamicalTable table;

    private Text           countPerPageText;

    private Text           pageNumberText;

    public PagingElement(final Composite parent, final DynamicalTable table) {

        super();
        this.table = table;
        createComposite(parent);
    }

    private void createComposite(final Composite parent) {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        createInfoPanel(parent);
        final Composite composite = createPaginationPanel(parent);
        createFirstButton(localizationUtil, composite);
        createPrevButton(localizationUtil, composite);
        createPageNumberText(composite);
        createNextButton(localizationUtil, composite);
        createLastButton(localizationUtil, composite);
    }

    private void createInfoPanel(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        final GridData gridData = new GridData(SWT.CENTER, SWT.CENTER,
                Boolean.FALSE, Boolean.FALSE);
        gridData.horizontalSpan = 3;
        composite.setLayoutData(gridData);
        composite.setLayout(new GridLayout(3, false));
        createLabelPerRage(composite);
        createItemsPerPageText(composite);
        createInfoLabel(composite);
    }

    private Composite createPaginationPanel(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        final GridData gridData = new GridData(SWT.CENTER, SWT.BOTTOM,
                Boolean.FALSE, Boolean.FALSE);
        composite.setLayoutData(gridData);
        composite.setLayout(new GridLayout(5, false));
        return composite;
    }

    private void createLabelPerRage(final Composite composite) {

        this.labelCountPerPage = new Label(composite, SWT.NONE);
    }

    private void createItemsPerPageText(final Composite composite) {

        this.countPerPageText = new Text(composite, SWT.NONE);
        this.countPerPageText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(final KeyEvent e) {

                // do nothing
            }

            @Override
            public void keyPressed(final KeyEvent e) {

                if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                    final String text = PagingElement.this.countPerPageText
                            .getText();
                    int number;
                    try {
                        number = Integer.parseInt(text);
                    } catch (final NumberFormatException exception) {
                        number = PagingElement.this.pagination
                                .getItemsPerPage();
                        PagingElement.this.countPerPageText.setText(Integer
                                .toString(number));
                        return;
                    }
                    PagingElement.this.pagination.setItemsPerPage(number);
                    update();
                }
            }
        });
    }

    private void createInfoLabel(final Composite composite) {

        this.labelInfo = new Label(composite, SWT.NONE);
    }

    private void createPrevButton(final LocalizationUtil localizationUtil,
            final Composite composite) {

        final Button prevButton = new Button(composite, SWT.NONE);
        prevButton.setText(localizationUtil.translate(Captions.PREV_BUTTON));
        prevButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                prevPressed();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                prevPressed();
            }

            private void prevPressed() {

                if (PagingElement.this.pagination.hasPreviousPage()) {
                    PagingElement.this.table
                            .updateInput(PagingElement.this.pagination
                                    .getPreviousPage());
                    updateInfo();

                }
            }
        });
    }

    private void createPageNumberText(final Composite composite) {

        this.pageNumberText = new Text(composite, SWT.NONE);
        this.pageNumberText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(final KeyEvent e) {

                // do nothing
            }

            @Override
            public void keyPressed(final KeyEvent e) {

                if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                    final String text = PagingElement.this.pageNumberText
                            .getText();
                    int pageNumber;
                    try {
                        pageNumber = Integer.parseInt(text);
                    } catch (final NumberFormatException exception) {
                        pageNumber = PagingElement.this.pagination
                                .getCurrentPage();
                        PagingElement.this.pageNumberText.setText(Integer
                                .toString(pageNumber));
                        return;
                    }
                    if (PagingElement.this.pagination.tryToPage(pageNumber)) {
                        update();
                    } else {
                        PagingElement.this.pageNumberText.setText(Integer
                                .toString(PagingElement.this.pagination
                                        .getCurrentPage()));
                    }
                }
            }

        });
    }

    private void createFirstButton(final LocalizationUtil localizationUtil,
            final Composite composite) {

        final Button firstButton = new Button(composite, SWT.NONE);
        firstButton.setText(localizationUtil.translate(Captions.FIRST_BUTTON));
        firstButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                firstPressed();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                firstPressed();
            }

            private void firstPressed() {

                final int firstPage = 1;
                if (PagingElement.this.pagination.tryToPage(firstPage)) {
                    update();
                }
            }
        });
    }

    private void createLastButton(final LocalizationUtil localizationUtil,
            final Composite composite) {

        final Button lastButton = new Button(composite, SWT.NONE);
        lastButton.setText(localizationUtil.translate(Captions.LAST_BUTTON));
        lastButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                lastPressed();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                lastPressed();
            }

            private void lastPressed() {

                final int lastPage = PagingElement.this.pagination
                        .getCountOfPages();
                if (PagingElement.this.pagination.tryToPage(lastPage)) {
                    update();
                }
            }
        });
    }

    private void createNextButton(final LocalizationUtil localizationUtil,
            final Composite composite) {

        final Button nextButton = new Button(composite, SWT.NONE);
        nextButton.setText(localizationUtil.translate(Captions.NEXT_BUTTON));
        nextButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                nextPressed();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                nextPressed();
            }

            private void nextPressed() {

                if (PagingElement.this.pagination.hasNextPage()) {
                    PagingElement.this.table
                            .updateInput(PagingElement.this.pagination
                                    .getNextPage());
                    updateInfo();
                }
            }
        });
    }

    private String generatePagingTextI() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final StringBuilder text = new StringBuilder();
        text.append(localizationUtil.translate(Captions.ITEM_COUNT))
                .append(this.pagination.getItemCount()).append(". ")
                .append(localizationUtil.translate(Captions.ITEMS_PER_PAGE));
        return text.toString();
    }

    private String generatePagingTextII() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final StringBuilder text = new StringBuilder();
        text.append(". ").append(localizationUtil.translate(Captions.PAGE))
                .append(' ').append(this.pagination.getCurrentPage())
                .append(' ').append(localizationUtil.translate(Captions.OF))
                .append(' ').append(this.pagination.getCountOfPages())
                .append('.');
        return text.toString();
    }

    public void updateInfo() {

        this.labelCountPerPage.setText(generatePagingTextI());
        this.countPerPageText.setText(Integer.toString(this.pagination
                .getItemsPerPage()));
        this.labelInfo.setText(generatePagingTextII());
        this.pageNumberText.setText(Integer.toString(this.pagination
                .getCurrentPage()));
    }

    public void setPagination(final Pagination pagination) {

        this.pagination = pagination;
        updateInfo();
    }

    private void update() {

        PagingElement.this.table.updateInput(PagingElement.this.pagination
                .getPage());
        updateInfo();
    }
}
