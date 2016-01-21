package br.com.leitor.seguranca.dao;

import br.com.leitor.dao.DB;
import br.com.leitor.seguranca.MacFilial;
import java.util.List;
import javax.persistence.Query;

public class MacFilialDao extends DB {

    public MacFilial findMacFilial(String mac) {
        try {
            Query query = getEntityManager().createQuery("SELECT MF FROM MacFilial AS MF WHERE MF.mac = :mac");
            query.setParameter("mac", mac);
            List list = query.getResultList();
            if (!list.isEmpty() && list.size() == 1) {
                return (MacFilial) list.get(0);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
