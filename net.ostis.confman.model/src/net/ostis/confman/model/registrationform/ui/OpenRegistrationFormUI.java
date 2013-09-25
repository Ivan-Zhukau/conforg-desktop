package net.ostis.confman.model.registrationform.ui;

import net.ostis.confman.model.registrationform.ui.listeners.ExitListener;
import net.ostis.confman.model.registrationform.ui.listeners.OpenListener;
import net.ostis.confman.model.registrationform.ui.listeners.SaveListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class OpenRegistrationFormUI {

    private Display  display;

    private Shell    shell;

    private Menu     menu;

    private MenuItem openItem;

    private MenuItem saveItem;

    private MenuItem exitItem;

    public OpenRegistrationFormUI() {

        setWindowOptions();
        initMenu();
        this.shell.open();

        while (!this.shell.isDisposed()) {
            if (!this.display.readAndDispatch()) {
                this.display.sleep();
            }
        }
        this.display.dispose();
    }

    private void initMenu() {

        this.menu = new Menu(this.shell, SWT.BAR);
        final MenuItem file = createItem(this.menu, "File", SWT.CASCADE);
        final Menu filemenu = new Menu(this.shell, SWT.DROP_DOWN);
        file.setMenu(filemenu);
        initItems(filemenu);
        this.shell.setMenuBar(this.menu);
    }

    private void initItems(final Menu filemenu) {

        this.openItem = createItem(filemenu, "Open", SWT.PUSH);
        this.openItem.addSelectionListener(new OpenListener(this.shell));
        this.saveItem = createItem(filemenu, "Save", SWT.PUSH);
        this.saveItem.addSelectionListener(new SaveListener(this.shell));
        final MenuItem separator = createItem(filemenu, "", SWT.SEPARATOR);
        this.exitItem = createItem(filemenu, "Exit", SWT.PUSH);
        this.exitItem.addSelectionListener(new ExitListener(this.shell));
    }

    private MenuItem createItem(final Menu filemenu, final String name,
            final int type) {

        final MenuItem item = new MenuItem(filemenu, type);
        item.setText(name);
        return item;
    }

    private void setWindowOptions() {

        this.display = new Display();
        this.shell = new Shell(this.display);
        this.shell.setSize(800, 500);
        this.shell.setText("Open registration form");
    }

    public static void main(final String[] argv) {

        new OpenRegistrationFormUI();
    }

}
