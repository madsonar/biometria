package br.com.leitor.pessoa.dao;

import br.com.leitor.dao.DB;
import br.com.leitor.pessoa.BiometriaAtualizaCatraca;
import javax.persistence.Query;

public class BiometriaAtualizaCatracaDao extends DB {

    public BiometriaAtualizaCatraca refresh(Integer device) {
        try {
            String queryString = "SELECT BAC.* FROM pes_biometria_atualiza_catraca AS BAC WHERE BAC.is_aparelho" + device + " = true AND BAC.id = 1";
            Query query = getEntityManager().createNativeQuery(queryString, BiometriaAtualizaCatraca.class);
            return (BiometriaAtualizaCatraca) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
