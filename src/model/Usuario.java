package model;

import enums.TipoUsuario;

public class Usuario {
    private String login;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Usuario(){}

    public Usuario(String login, String senha, TipoUsuario tipoUsuario) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void cadastrarUsuario(String login, String senha, TipoUsuario tipoUsuario) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
