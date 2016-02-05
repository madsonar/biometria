package br.com.leitor.main;

import br.com.leitor.dao.Dao;
import br.com.leitor.pessoa.BiometriaServidor;
import br.com.leitor.pessoa.dao.BiometriaDao;
import br.com.leitor.seguranca.Conf;
import br.com.leitor.seguranca.MacFilial;
import br.com.leitor.seguranca.dao.MacFilialDao;
import br.com.leitor.utils.Mac;
import java.util.List;
import rtools.WSStatus;
import rtools.WebService;

public class Close {

    public static void confirm() {
        Conf conf = new Conf();
        conf.loadJson();
        WebService webService = new WebService();
        if (conf.getWeb_service()) {
            webService.PUT("biometria_habilitar.jsf", "", "habilitar=false");
            try {
                webService.execute();
                WSStatus wSStatus = webService.wSStatus();
                if (wSStatus.getCodigo() == 0) {
                    webService.PUT("biometria_limpar.jsf", "", "");
                    try {
                        webService.execute();
                    } catch (Exception ex) {
                    }
                }
            } catch (Exception ex) {
            }
        } else {
            BiometriaDao biometriaDao = new BiometriaDao();
            List<BiometriaServidor> list = biometriaDao.pesquisaStatusPorComputador(getMacFilial().getId());
            if (!list.isEmpty()) {
                BiometriaServidor biometriaServidor = list.get(0);
                biometriaServidor.setDataAtivo(null);
                biometriaServidor.setAtivo(false);
                Dao dao = new Dao();
                dao.update(biometriaServidor, true);
            }
            list.clear();
            list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(getMacFilial().getId());
            if (list.isEmpty()) {
                Dao dao = new Dao();
                for (BiometriaServidor list1 : list) {
                    dao.delete(list1, true);
                }
            }

        }
    }

    public static void clear() {
        Conf conf = new Conf();
        WebService webService = new WebService();
        conf.loadJson();
        try {
            if (conf.getWeb_service()) {
                webService.PUT("biometria_limpar.jsf", "", "");
                webService.execute();
            } else {
                BiometriaDao biometriaDao = new BiometriaDao();
                Dao dao = new Dao();
                List list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(getMacFilial().getId());
                if (!list.isEmpty()) {
                    for (Object list1 : list) {
                        dao.delete(list1, true);
                    }
                }

            }
        } catch (Exception e) {

        }
    }

    private static MacFilial getMacFilial() {
        String mac = Mac.getInstance();
        MacFilialDao macFilialDao = new MacFilialDao();
        MacFilial macFilial = macFilialDao.findMacFilial(mac);
        if (macFilial == null) {
            return null;
        }
        return macFilial;
    }

}
