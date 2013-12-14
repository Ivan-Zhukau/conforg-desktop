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
        PREV_BUTTON("pagingPrevBtn"),
        NEXT_BUTTON("pagingNextBtn"),
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

    private Label          label;

    private DynamicalTable table;

    private Text           pageNumberText;

    public PagingElement(final Composite parent, final DynamicalTable table) {

        super();
        this.table = table;
        createComposite(parent);
    }

    private void createComposite(final Composite parent) {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final Composite composite = createPaginationPanel(parent);
        createInfoLabel(composite);
        createPrevButton(localizationUtil, composite);
        createPageNumberText(composite);
        createNextButton(localizationUtil, composite);

    }

    private Composite createPaginationPanel(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        final GridData gridData = new GridData(SWT.CENTER, SWT.BOTTOM,
                Boolean.FALSE, Boolean.FALSE);
        composite.setLayoutData(gridData);
        composite.setLayout(new GridLayout(3, false));
        return composite;
    }

    private void createInfoLabel(final Composite composite) {

        GridData gridData;
        this.label = new Label(composite, SWT.NONE);
        gridData = new GridData(SWT.CENTER, SWT.CENTER, Boolean.FALSE,
                Boolean.FALSE);
        gridData.horizontalSpan = 3;
        this.label.setLayoutData(gridData);
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
                        PagingElement.this.table
                                .updateInput(PagingElement.this.pagination
                                        .getPage());
                        updateInfo();
                    } else {
                        PagingElement.this.pageNumberText.setText(Integer
                                .toString(PagingElement.this.pagination
                                        .getCurrentPage()));
                    }
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

    private String generatePagingText() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final StringBuilder text = new StringBuilder();
        text.append(localizationUtil.translate(Captions.ITEM_COUNT))
                .append(this.pagination.getItemCount()).append(". ")
                .append(localizationUtil.translate(Captions.PAGE)).append(' ')
                .append(this.pagination.getCurrentPage()).append(' ')
                .append(localizationUtil.translate(Captions.OF)).append(' ')
                .append(this.pagination.getCountOfPages()).append('.');
        return text.toString();
    }

    public void updateInfo() {

        this.label.setText(generatePagingText());
        this.pageNumberText.setText(Integer.toString(this.pagination
                .getCurrentPage()));
    }

    public void setPagination(final Pagination pagination) {

        this.pagination = pagination;
        updateInfo();
    }
}
