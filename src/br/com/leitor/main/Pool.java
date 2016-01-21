package br.com.leitor.main;

import br.com.leitor.pessoa.BiometriaCaptura;
import br.com.leitor.pessoa.dao.BiometriaDao;
import br.com.leitor.seguranca.MacFilial;
import br.com.leitor.seguranca.dao.MacFilialDao;
import br.com.leitor.utils.Mac;
import java.util.List;

public class Pool {

    private BiometriaCaptura biometriaCaptura;

    public Pool() {
        this.biometriaCaptura = null;
    }

    public Pool(BiometriaCaptura biometriaCaptura) {
        this.biometriaCaptura = biometriaCaptura;
    }

    public BiometriaCaptura getBiometriaCaptura() {
        if (biometriaCaptura == null) {
            String mac = Mac.getInstance();
            MacFilialDao macFilialDao = new MacFilialDao();
            MacFilial macFilial = macFilialDao.findMacFilial(mac);
            if (macFilial == null) {
                biometriaCaptura = null;
                return null;
            }
            
            BiometriaDao biometriaDao = new BiometriaDao();
            List list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(macFilial.getId());
            if (!list.isEmpty()) {
                biometriaCaptura = (BiometriaCaptura) list.get(0);
            }
        }
        return biometriaCaptura;
    }

    public void setBiometriaCaptura(BiometriaCaptura biometriaCaptura) {
        this.biometriaCaptura = biometriaCaptura;
    }

}
