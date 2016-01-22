package br.com.leitor.pessoa.dao;

import br.com.leitor.dao.DB;
import br.com.leitor.pessoa.Biometria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class BiometriaDao extends DB {

    public List pesquisaStatusPorComputador(Integer macFilial) {
        try {
            getEntityManager().clear();
            Query query = getEntityManager().createQuery("SELECT BS FROM BiometriaServidor AS BS WHERE BS.macFilial.id = :macFilial  ");
            query.setParameter("macFilial", macFilial);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {

        }
        return new ArrayList();
    }

    public List pesquisaBiometriaCapturaPorPessoa(Integer pessoa) {
        try {
            getEntityManager().clear();
            Query query = getEntityManager().createQuery("SELECT BC FROM BiometriaCaptura AS BC WHERE BC.pessoa.id = :pessoa ");
            query.setParameter("pessoa", pessoa);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {

        }
        return new ArrayList();
    }

    public List pesquisaBiometriaCapturaPorMacFilial(Integer macFilial) {
        try {
            getEntityManager().clear();
            Query query = getEntityManager().createQuery("SELECT BC FROM BiometriaCaptura AS BC WHERE BC.macFilial.id = :macFilial ");
            query.setParameter("macFilial", macFilial);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {

        }
        return new ArrayList();
    }

    public Biometria pesquisaBiometriaPorPessoa(Integer pessoa) {
        try {
            Query query = getEntityManager().createQuery("SELECT B FROM Biometria AS B WHERE B.pessoa.id = :pessoa ");
            query.setParameter("pessoa", pessoa);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (Biometria) list.get(0);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List listaBiometriaDepartamentoPorPessoa(Integer pessoa) {
        try {
            Query query = getEntityManager().createQuery("SELECT BD FROM BiometriaDepartamento AS BD WHERE BD.biometria.pessoa.id = :pessoa ");
            query.setParameter("pessoa", pessoa);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {

        }
        return new ArrayList();
    }

    public Biometria pesquisaBiometriaPorString(String biometria) {
        try {
            Query query = getEntityManager().createQuery("SELECT B FROM Biometria AS B WHERE B.biometria LIKE :biometria");
            query.setParameter("biometria", biometria);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (Biometria) list.get(0);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List listBiometriaArray() {
        try {
            getEntityManager().clear();
            Query query = getEntityManager().createQuery("SELECT BC.biometria FROM Biometria AS BC ORDER BY BC.id");
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {

        }
        return new ArrayList();
    }

    public List listBiometria() {
        try {
            getEntityManager().clear();
            Query query = getEntityManager().createNativeQuery("SELECT b.* FROM pes_biometria AS b WHERE b.is_ativo = true AND (b.ds_biometria IS NOT NULL AND B.ds_biometria <> '') ORDER BY b.id", Biometria.class);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {

        }
        return new ArrayList();
    }

    public List reloadListBiometria(Integer deviceNumber) {
        try {
            getEntityManager().clear();
            Query query = getEntityManager().createNativeQuery("SELECT b.* FROM pes_biometria AS b WHERE b.is_ativo = true AND (b.ds_biometria IS NOT NULL OR B.ds_biometria <> '') AND B.dt_atualiza_aparelho" + deviceNumber + " IS NOT NULL ORDER BY B.id", Biometria.class);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {

        }
        return new ArrayList();
    }

    public Integer total() {
        try {
            getEntityManager().clear();
            Query query = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM pes_biometria AS b WHERE b.is_ativo = true AND (b.ds_biometria IS NOT NULL AND B.ds_biometria <> '') ");
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return Integer.parseInt(((List) list.get(0)).get(0).toString());
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return 0;
    }

    public Boolean reload() {
        try {
            getEntityManager().getTransaction().begin();
            Query query = getEntityManager().createNativeQuery("UPDATE pes_biometria SET dt_atualiza_aparelho1 = null, dt_atualiza_aparelho2 = null, dt_atualiza_aparelho3 = null, dt_atualiza_aparelho4 = null");
            if (query.executeUpdate() == 0) {
                getEntityManager().getTransaction().rollback();
                return false;
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            return false;
        }
        return true;
    }

    public Boolean reload(Integer deviceNumber) {
        try {
            getEntityManager().getTransaction().begin();
            Query query = getEntityManager().createNativeQuery("UPDATE pes_biometria SET dt_atualiza_aparelho" + deviceNumber + " = null");
            if (query.executeUpdate() == 0) {
                getEntityManager().getTransaction().rollback();
                return false;
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            return false;
        }
        return true;
    }

}
