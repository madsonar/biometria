package br.com.leitor.pessoa.dao;

import br.com.leitor.dao.DB;
import br.com.leitor.dao.Dao;
import br.com.leitor.pessoa.BiometriaCatraca;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class BiometriaCatracaDao extends DB {

    public void destroy(String ip) {
        try {
            Query query = getEntityManager().createQuery("SELECT BC FROM BiometriaCatraca AS BC WHERE BC.ip = :ip");
            query.setParameter("ip", ip);
            List<BiometriaCatraca> list = query.getResultList();
            for (int i = 0; i < list.size(); i++) {
                new Dao().delete(list.get(i), true);
            }
        } catch (Exception e) {
        }
    }

    public List findByPessoa(Integer pessoa) {
        try {
            Query query = getEntityManager().createQuery("SELECT BC FROM BiometriaCatraca AS BC WHERE BC.pessoa.id = :pessoa");
            query.setParameter("pessoa", pessoa);
            List<?> list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {
        }
        return new ArrayList();
    }

}
