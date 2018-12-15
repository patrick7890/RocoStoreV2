/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Usuario;
import facade.UsuarioFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Patricio
 */
@Named(value = "LoginController")
@Dependent
public class LoginController {

    @EJB
    private UsuarioFacade usuarioFacade;
    private String pwd;
    private String user;

    public LoginController() {
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario aux = usuarioFacade.validate(user, pwd);
        if (aux != null) {
            HttpSession ses = (HttpSession) context.getExternalContext().getSession(false);
            ses.setAttribute("user", aux);
            context.getExternalContext().redirect("/rocostore/faces/index.xhtml");
        } else {

            context.addMessage(null, new FacesMessage("Login Error", "Usuario o contraseña incorrecto"));
            context.getExternalContext().redirect("/rocostore/faces/login.xhtml");
        }

    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/rocostore/faces/login.xhtml");
    }
}
