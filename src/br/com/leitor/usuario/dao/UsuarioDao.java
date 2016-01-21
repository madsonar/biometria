package br.com.leitor.usuario.dao;

import br.com.leitor.dao.DB;
import br.com.leitor.usuario.Usuario;
import java.util.List;
import javax.persistence.Query;

public class UsuarioDao extends DB {

    public Usuario findUsuario(String login, String senha) {
        Usuario usuario = null;
        try {
            Query query = getEntityManager().createQuery("SELECT U FROM Usuario AS U  WHERE U.login = :login AND U.senha = :senha");
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (Usuario) list.get(0);
            }
//            while (rs.next()) {
//                usuario = new Usuario();
//                usuario.setId(rs.getInt("id"));
//                usuario.setPessoa(rs.getInt("id_pessoa"));
//                usuario.setLogin(rs.getString("ds_login"));
//                usuario.setSenha(rs.getString("ds_senha"));
//                usuario.setAtivo(rs.getBoolean("is_ativo"));
//            }
        } catch (Exception e) {
            return usuario;
        }
        return usuario;
    }
}
