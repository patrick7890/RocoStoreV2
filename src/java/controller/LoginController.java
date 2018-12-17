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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Patricio
 */
@ManagedBean(name = "loginController")
@SessionScoped
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
            context.getExternalContext().redirect("/RocoStoreV2/faces/index.xhtml");
        } else {

            context.addMessage(null, new FacesMessage("Login Error", "Usuario o contraseña incorrecto"));
            context.getExternalContext().redirect("/RocoStoreV2/faces/login.xhtml");
        }

    }

    public void loginAdmin() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario aux = usuarioFacade.validateAdmin(user, pwd);
        if (aux != null) {
            HttpSession ses = (HttpSession) context.getExternalContext().getSession(false);
            ses.setAttribute("user", aux);
            context.getExternalContext().redirect("/RocoStoreV2/faces/admin.xhtml");
        } else {

            context.addMessage(null, new FacesMessage("Login Error", "Usuario o contraseña incorrecto"));
            context.getExternalContext().redirect("/RocoStoreV2/faces/admin.xhtml");
        }

    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/RocoStoreV2/faces/index.xhtml");
    }

}
