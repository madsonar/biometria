package br.com.leitor.main;

import br.com.leitor.pessoa.BiometriaCaptura;
import br.com.leitor.pessoa.dao.BiometriaDao;
import br.com.leitor.seguranca.Conf;
import br.com.leitor.seguranca.MacFilial;
import br.com.leitor.seguranca.dao.MacFilialDao;
import br.com.leitor.utils.Mac;
import br.com.leitor.webservice.classes.WSBiometriaCaptura;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rtools.WebService;

public class Pool {

    private BiometriaCaptura biometriaCaptura;

    public Pool() {
        this.biometriaCaptura = null;
    }

    public Pool(BiometriaCaptura biometriaCaptura) {
        this.biometriaCaptura = biometriaCaptura;
    }

    public BiometriaCaptura getBiometriaCaptura() {
        String mac = Mac.getInstance();
        if (biometriaCaptura == null) {
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

    public Boolean captura() {
        Conf conf = new Conf();
        conf.loadJson();
        if (conf.getWeb_service()) {
            try {
                WebService webService = new WebService();
                webService.action("pedido_captura");
                webService.GET("biometria_captura");
                webService.execute();
                try {
                    WSBiometriaCaptura wsbc = (WSBiometriaCaptura) webService.object(new WSBiometriaCaptura());
                    if (wsbc != null) {
                        return true;
                    }

                } catch (Exception e) {
                    return false;
                }
            } catch (Exception ex) {
                return false;
            }
        } else if (getBiometriaCaptura() != null) {
            return true;
        }
        return false;
    }

}
