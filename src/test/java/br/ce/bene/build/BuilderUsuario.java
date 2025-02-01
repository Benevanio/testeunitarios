package br.ce.bene.build;

import br.ce.bene.entities.Usuario;

public class BuilderUsuario {
    private Usuario usuario;

    private BuilderUsuario() {
    }

    public static BuilderUsuario umUsuario() {
        BuilderUsuario builder = new BuilderUsuario();
        builder.usuario = new Usuario("Usuario 1");
        return builder;
    }

    public Usuario agora() {
        return usuario;
    }

}
