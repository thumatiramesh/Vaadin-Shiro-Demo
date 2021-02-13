package com.vaadinshiro.ui;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("userhome")
public class UserHome extends VerticalLayout{
	
	private H2 h2;
	private H3 h3;
	private Button buttonLogout;
	private Button compose;
	private Button save;
	private Button delete;
	private Button deleteAuthorAndEditor;
	
	
	public UserHome() {
		this.h2 = new H2("Welcome to Vaadin+Shiro World");
		this.h3 = new H3();
		this.buttonLogout = new Button("Logout");
		this.compose = new Button("Compose Articles");
		this.save = new Button("Save Articles");
		this.delete = new Button("Delete Articles");
		this.deleteAuthorAndEditor = new Button("Delete Author/Editor");
		
			addLogoutButton();
			buildParagramWithUserName();
			placeAccessButtonLayout();
	}

	private void placeAccessButtonLayout() {
		Subject currentUser = SecurityUtils.getSubject();
		HorizontalLayout accessButtons = new HorizontalLayout();
		accessButtons.add(compose, save, delete, deleteAuthorAndEditor);
		add(accessButtons);
		
		compose.addClickListener(compose -> {
			if (currentUser.hasRole("admin") || currentUser.hasRole("editor") || currentUser.hasRole("author")) { 
				Notification.show("Hey, You can compose the articles");
			} else {
				Notification.show("Sorry, You don't have access to compose the articles");
			}
		});
		
		save.addClickListener(save -> {
			if (currentUser.hasRole("admin") || currentUser.hasRole("editor") || currentUser.hasRole("author")) { 
				Notification.show("Hey, You can save articles");
			} else {
				Notification.show("Sorry, You don't have access to save the articles");
			}
		});
		
		delete.addClickListener(delete -> {
			if (currentUser.hasRole("admin") || currentUser.hasRole("editor")) { 
				Notification.show("Hey, You can delete the articles");
			} else {
				Notification.show("Sorry, You don't have access to delete the articles");
			}
		});
		
		deleteAuthorAndEditor.addClickListener(deleteAuthor -> {
			if (currentUser.hasRole("admin")) { 
				Notification.show("Hey, You can delete Author's / Editor's");
			} else {
				Notification.show("Sorry, You don't have access to delete the Author's / Editor's");
			}
		});
		
	}

	private void addLogoutButton() {
		add(buttonLogout, h2);
		buttonLogout.addClickListener( out -> {
			SecurityUtils.getSubject().logout();
			UI.getCurrent().navigate("login");
		});
	}

	private void buildParagramWithUserName() {
		h3.add("Hello "+ (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal());
		add(h3);
		
	}

}
