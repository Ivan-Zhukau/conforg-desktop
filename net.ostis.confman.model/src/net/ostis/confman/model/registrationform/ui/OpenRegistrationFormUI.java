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
	private Display display;
	private Shell shell;
	private Menu menu;
	private MenuItem openItem;
	private MenuItem saveItem ;
	private MenuItem exitItem;

	public OpenRegistrationFormUI() {
		  setWindowOptions();   
		  initMenu();
		  shell.open();
	
		  while (!shell.isDisposed()) {
			  if (!display.readAndDispatch()){
				  display.sleep();			  
			  }
	    }
	    display.dispose();
	}
	
	private void initMenu() {
	    menu = new Menu(shell, SWT.BAR);
	    MenuItem file = createItem(menu,"File",SWT.CASCADE);
	    Menu filemenu = new Menu(shell, SWT.DROP_DOWN);
	    file.setMenu(filemenu);
	    initItems(filemenu);	
	    shell.setMenuBar(menu);
	}
	
	private void initItems(Menu filemenu) {
		openItem = createItem(filemenu,"Open",SWT.PUSH);
	    openItem.addSelectionListener(new OpenListener(shell));
	    saveItem = createItem(filemenu,"Save",SWT.PUSH);
	    saveItem.addSelectionListener(new SaveListener(shell));
	    MenuItem separator =  createItem(filemenu,"",SWT.SEPARATOR);
	    exitItem = createItem(filemenu,"Exit",SWT.PUSH);    
	    exitItem.addSelectionListener(new ExitListener(shell));
	}
	
	private MenuItem createItem(final Menu filemenu, String name, int type) {
		MenuItem item = new MenuItem(filemenu, type);
	    item.setText(name);
	    return item;
	}
	
	private void setWindowOptions() {
		  display = new Display();
		  shell = new Shell(display);
		  shell.setSize(800, 500);	    
		  shell.setText("Open registration form");	
	}
	
	public static void main(String[] argv) {
	    new OpenRegistrationFormUI();
	  }

}
