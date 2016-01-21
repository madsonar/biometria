/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leitor.utils;

import br.com.leitor.usuario.Usuario;

public class Session {

    private static Session INSTANCE = null;
    private Usuario usuario;

    public Session() {
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public static Session getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Session();
        }
        return INSTANCE;
    }
}
