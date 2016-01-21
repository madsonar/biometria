package br.com.leitor.dao;

import br.com.leitor.seguranca.Conf;
import br.com.leitor.sistema.Configuracao;
import br.com.leitor.sistema.conf.DataBase;
import br.com.leitor.utils.Logs;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import oracle.toplink.essentials.config.CacheType;
import oracle.toplink.essentials.config.TopLinkProperties;
import oracle.toplink.essentials.ejb.cmp3.EntityManagerFactoryProvider;

public class DB {

    private EntityManager entidade;

    public EntityManager getEntityManager() {
        if (entidade == null) {
            Conf conf = new Conf();
            conf.loadJson();
            String databaseName = "";
            if (!conf.getClient().isEmpty()) {
                databaseName = conf.getClient();
                Configuracao configuracao = servidor(databaseName);
                DataBase dataBase = new DataBase();
                dataBase.loadJson();
                if (!dataBase.getHost().isEmpty()) {
                    configuracao.setHost(dataBase.getHost());
                }
                if (!dataBase.getDatabase().isEmpty()) {
                    configuracao.setPersistence(dataBase.getDatabase());
                }
                if (!dataBase.getPassword().isEmpty()) {
                    configuracao.setSenha(dataBase.getPassword());
                }
                try {
                    String dados_conexao = "Biometria - " + conf.getClient() + " - " + conf.getFilial() + " - " + conf.getDevice() + " - " + conf.getModel() + " - " + conf.getBrand();
                    Map properties = new HashMap();
                    properties.put(TopLinkProperties.CACHE_TYPE_DEFAULT, CacheType.NONE);
                    // properties.put(TopLinkProperties.CACHE_TYPE_DEFAULT, CacheType.SoftWeak);
                    //properties.put(TopLinkProperties.CACHE_TYPE_DEFAULT, CacheType.Full);
                    properties.put(TopLinkProperties.JDBC_USER, "postgres");
                    properties.put(TopLinkProperties.TRANSACTION_TYPE, "RESOURCE_LOCAL");
                    properties.put(TopLinkProperties.JDBC_DRIVER, "org.postgresql.Driver");
                    properties.put(TopLinkProperties.JDBC_PASSWORD, configuracao.getSenha());
                    properties.put(TopLinkProperties.JDBC_URL, "jdbc:postgresql://" + configuracao.getHost() + ":5432/" + configuracao.getPersistence() + "?ApplicationName=" + dados_conexao);
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory(configuracao.getPersistence(), properties);
                    String createTable = "";
                    if (createTable.equals("criar")) {
                        properties.put(EntityManagerFactoryProvider.DDL_GENERATION, EntityManagerFactoryProvider.CREATE_ONLY);
                    }
                    entidade = emf.createEntityManager();
                } catch (Exception e) {
                    return null;
                }
            } else {
                Logs logs = new Logs();
                logs.save("DB -> Erro", "Cliente não encontrado!");
                System.exit(0);
            }
        }
        return entidade;
    }

    public Configuracao servidor(String cliente) {
        Configuracao configuracao = new Configuracao();
        switch (cliente) {
            case "ComercioAraras":
            case "ComercioLimeiraTeste":
            case "ComercioSertaozinho":
            case "FederacaoBH":
            case "SinpaaeRP":
            case "VestuarioLimeira":
            case "ComercioItapetininga":
            case "SeaacRP":
            case "MetalRP":
            case "ExtrativaRP":
            case "AlimentacaoArceburgo":
            case "FederacaoExtrativaSP":
            case "ExtrativaSP":
            case "HoteleiroRP":
            case "GaragistaRP":
            case "MetalBatatais":
            case "ServidoresRP":
            case "SeaacFranca":
            case "SincovagaSP":
            case "GraficosRP":
                configuracao.setCaminhoSistema(cliente);
                configuracao.setPersistence(cliente);
                configuracao.setHost("192.168.1.102");
                configuracao.setSenha("r#@tools");
                break;
            case "Rtools":
                configuracao.setCaminhoSistema(cliente);
                configuracao.setPersistence(cliente);
                configuracao.setHost("192.168.1.102");
                configuracao.setSenha("r#@tools");
                break;
            case "ComercioLimeira":
                configuracao.setCaminhoSistema(cliente);
                configuracao.setPersistence(cliente);
                // IP LOCAL: 192.168.0.201
                // IP EXTERNO: 200.204.32.23
                configuracao.setHost("200.204.32.23");
                configuracao.setSenha("r#@tools");
                break;
            case "NovaBase":
                configuracao.setCaminhoSistema(cliente);
                configuracao.setPersistence(cliente);
                configuracao.setHost("192.168.1.60");
                configuracao.setSenha("989899");
                break;
            case "ComercioRP":
                configuracao.setCaminhoSistema(cliente);
                configuracao.setPersistence("Sindical");
                configuracao.setHost("200.152.187.241");
                configuracao.setSenha("989899");
                break;
            default:
                if (cliente.equals("Sindical")) {
                    // -- ATUAL
                    cliente = "ComercioRP";
                    configuracao.setHost("192.168.1.102");
                    configuracao.setSenha("r#@tools");
                }   //            } else {
//                if (cliente.equals("ServidoresRP")) {
//                    configuracao.setHost("localhost");
//                    configuracao.setSenha("989899");
//                }
//            }
                configuracao.setCaminhoSistema(cliente);
                configuracao.setPersistence(cliente);
                break;
        }
        return configuracao;
    }
    // COMÉRCIO LIMEIRA
//    public Configuracao servidor(String cliente) {
//        Configuracao configuracao = new Configuracao();
//        configuracao.setCaminhoSistema(cliente);
//        configuracao.setPersistence(cliente);
//        // IP LOCAL: 192.168.0.201
//        // IP EXTERNO: 200.204.32.23
//        configuracao.setHost("192.168.0.201");
//        configuracao.setSenha("r#@tools");
//        return configuracao;
//    }
    // COMÉRCIO RIBEIRÃO
//    public Configuracao servidor(String cliente) {
//        Configuracao configuracao = new Configuracao();
//        configuracao.setCaminhoSistema("Sindical");
//        configuracao.setHost("localhost");
//        configuracao.setSenha("989899");
//        configuracao.setPersistence(cliente);
//        return configuracao;
//    }
}
