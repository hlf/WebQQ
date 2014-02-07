package com.ltype.webqq.panel;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class Panel {
	protected Shell shellWebqq;
	public Text userText;
	public Text passwordText;
	public Text checkText;
	public Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Panel window = new Panel();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shellWebqq.open();
		shellWebqq.layout();
		while (!shellWebqq.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellWebqq = new Shell();
		shellWebqq.setSize(450, 300);
		shellWebqq.setText("WebQQ");
		
		userText = new Text(shellWebqq, SWT.BORDER);
		userText.setBounds(150, 50, 165, 20);

		passwordText = new Text(shellWebqq, SWT.BORDER);
		passwordText.setBounds(150, 100, 165, 20);
		
		checkText = new Text(shellWebqq, SWT.BORDER);
		checkText.setBounds(150, 150, 60, 20);
		
		Label userLabel = new Label(shellWebqq, SWT.NONE);
		userLabel.setBounds(94, 50, 50, 23);
		userLabel.setText("账号");
		
		Label passwordLabel = new Label(shellWebqq, SWT.NONE);
		passwordLabel.setText("密码");
		passwordLabel.setBounds(94, 100, 50, 23);
		
		Button loginButton = new Button(shellWebqq, SWT.NONE);
		loginButton.setBounds(255, 230, 60, 27);
		loginButton.setText("登录");
		
		Label checkLabel = new Label(shellWebqq, SWT.NONE);
		checkLabel.setText("验证码");
		checkLabel.setBounds(94, 150, 50, 23);
		
		final Label imageLabel = new Label(shellWebqq, SWT.NONE);
		imageLabel.setBounds(150, 175, 130, 53);
		

		loginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/*
				MessageBox dialog = new MessageBox(shell,SWT.OK|SWT.ICON_INFORMATION);
				dialog.setMessage(DownText.getText());
				dialog.open();
				*/                
				try {
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
