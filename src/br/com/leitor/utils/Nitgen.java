package br.com.leitor.utils;

import br.com.leitor.dao.Dao;
import br.com.leitor.main.Close;
import br.com.leitor.pessoa.Biometria;
import br.com.leitor.pessoa.BiometriaErro;
import br.com.leitor.pessoa.Pessoa;
import br.com.leitor.pessoa.dao.BiometriaDao;
import br.com.leitor.pessoa.dao.BiometriaErroDao;
import br.com.leitor.seguranca.Conf;
import br.com.leitor.seguranca.MacFilial;
import com.nitgen.SDK.BSP.NBioBSPJNI;
import com.nitgen.SDK.BSP.NBioBSPJNI.DEVICE_ENUM_INFO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Nitgen {

    private NBioBSPJNI nBioBSP;
    private NBioBSPJNI.IndexSearch indexSearch;
    private NBioBSPJNI.DEVICE_ENUM_INFO device;
    private Integer digitalCapturada;
    private String digitalCapturadaString;
    private Pessoa pessoa;
    private Biometria biometria;
    private Boolean showBiometria;
    private Boolean hardware;
    private Boolean open;
    private Boolean load;
    private Short device_code;
    private String device_name;
    private Integer deviceNumber;
    private Integer device_start;
    private Conf conf;

    public Nitgen() throws Exception {
        try {
            this.nBioBSP = null;
            this.indexSearch = null;
            this.device = null;
            this.device_name = null;
            this.digitalCapturada = null;
            this.digitalCapturadaString = null;
            this.showBiometria = false;
            this.load = true;
            this.conf = new Conf();
            conf.loadJson();
        } catch (NoSuchFieldError | UnsatisfiedLinkError | Exception e) {
            Logs logs = new Logs();
            logs.save("menu", "Erro ao carregar DLL. " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar DLL. " + e.getMessage());
            throw new Exception("Erro ao carregar DLL. " + e.getMessage());
        }
    }

    public void loadHardware() throws Exception {
        open = false;
        try {
            this.nBioBSP = new NBioBSPJNI();
            this.device = this.nBioBSP.new DEVICE_ENUM_INFO();
            this.nBioBSP.EnumerateDevice(this.device);
            this.indexSearch = this.nBioBSP.new IndexSearch();
        } catch (NoSuchFieldError | UnsatisfiedLinkError | Exception e) {
            Logs logs = new Logs();
            logs.save("menu", "Erro ao carregar DLL. " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar DLL. " + e.getMessage());
            throw new Exception("Erro ao carregar DLL. " + e.getMessage());
        }
        int n = this.device.DeviceCount;
        device_start = n;
        if (n == 0) {
            setHardware(false);
        } else {
            setHardware(true);
        }
    }

    /**
     * 0 - Erro 1 - Sucesso 2 - Processo cancelado
     *
     * @return
     */
//    public Integer readSave() {
//        if (hardware) {
//            for (int i = 0; i < device.DeviceCount; i++) {
//                try {
//                    int auto = device.DeviceInfo[i].AutoOn;
//                    if (auto == 1) {
//                    }
//                    nBioBSP.OpenDevice(device.DeviceInfo[i].NameID, device.DeviceInfo[i].Instance);
//                } catch (Exception e) {
//
//                }
//            }
//            BiometriaDao biometriaDao = new BiometriaDao();
//            biometria = new Biometria();
//            biometria = biometriaDao.pesquisaBiometriaPorPessoa(pessoa.getId());
//            Dao dao = new Dao();
//            int userID;
//            try {
//                userID = pessoa.getId();
//            } catch (NumberFormatException e) {
//                return 0;
//            }
//            if (biometria == null) {
//                biometria = new Biometria();
//                biometria.setLancamento(DataHoje.dataHoje());
//                biometria.setPessoa(pessoa);
//                biometria.setAtivo(true);
//                if (!dao.save(biometria, true)) {
//                    return 0;
//                }
//            } else {
//                biometria = (Biometria) dao.rebind(biometria);
//            }
//            NBioBSPJNI.FIR_HANDLE hFIR = nBioBSP.new FIR_HANDLE();
//            nBioBSP.Enroll(hFIR, null);
//            if (checkError()) {
//                if (nBioBSP.GetErrorCode() == NBioBSPJNI.ERROR.NBioAPIERROR_FUNCTION_FAIL) {
//                    nBioBSP.Capture(hFIR);
//                }
//            }
//            NBioBSPJNI.INPUT_FIR inputFIR = nBioBSP.new INPUT_FIR();
//            NBioBSPJNI.IndexSearch.SAMPLE_INFO sampleInfo = indexSearch.new SAMPLE_INFO();
//            inputFIR.SetFIRHandle(hFIR);
//            NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = nBioBSP.new FIR_TEXTENCODE();
//            //Pega a string de caracteres a partir do handle capturado
//            //Declara o input_fir para fazer a comparação da digital
//            inputFIR.SetTextFIR(textSavedFIR);
//            NBioBSPJNI.IndexSearch.FP_INFO fpInfo = indexSearch.new FP_INFO();
//            /* Faz o processo de identificação:
//             * - Primeiro parametro: digital capturada no momento
//             * - Segundo parametro: nivel de segurança. varia de 1 a 9. 5 é default.
//             * - Terceiro parametro: informação do usuário
//             * */
//            indexSearch.Identify(inputFIR, 5, fpInfo);
//            indexSearch.AddFIR(inputFIR, userID, sampleInfo);
//
//            if (checkError()) {
//                return 0;
//            }
//
//            this.digitalCapturada = nBioBSP.GetTextFIRFromHandle(hFIR, textSavedFIR);
//            this.digitalCapturadaString = textSavedFIR.TextFIR;
//            nBioBSP.CloseDevice(device.DeviceInfo[0].NameID, device.DeviceInfo[0].Instance);
//            if (this.digitalCapturadaString == null) {
//                biometria.setAtivo(false);
//                dao.update(biometria, true);
//                return 2;
//            }
//            biometria.setBiometria(digitalCapturadaString);
//            if (!biometria.isAtivo()) {
//                Object[] options = {"Sim", "Não"};
//                int resposta = JOptionPane.showOptionDialog(null,
//                        "Tem certeza que reativar esta biometria?", "Mensagem do Programa",
//                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
//                        options, options[0]);
//                if (resposta == 0) {
//                    List list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(MacFilial.getAcessoFilial().getId());
//                    if (!list.isEmpty()) {
//                        biometria.setAtivo(true);
//                        if (!dao.update(biometria, true)) {
//                            return 0;
//                        }
//                    }
//                    // OPERAÇÃO CANCELADA PELO USUÁRIO - SERVIDOR
//                    return 4;
//
//                }
//            } else {
//                if (!dao.update(biometria, true)) {
//                    return 0;
//                }
//            }
//
//            Close.clear();
//            hFIR.dispose();
//            hFIR = null;
//        } else {
//            return 3;
//        }
//
//        return 1;
//    }
    public Integer readSave() {
        if (hardware) {
            BiometriaDao biometriaDao = new BiometriaDao();
            biometria = new Biometria();
            biometria = biometriaDao.pesquisaBiometriaPorPessoa(pessoa.getId());
            Dao dao = new Dao();
            int userID;
            try {
                userID = pessoa.getId();
            } catch (NumberFormatException e) {
                return 0;
            }
            if (biometria == null) {
                biometria = new Biometria();
                biometria.setLancamento(DataHoje.dataHoje());
                biometria.setPessoa(pessoa);
                biometria.setAtivo(true);
                if (!dao.save(biometria, true)) {
                    return 0;
                }
            } else {
                biometria = (Biometria) dao.rebind(biometria);
            }
            switch (conf.getBrand().toLowerCase()) {
                case "nitgen":
                    for (int i = 0; i < device.DeviceCount; i++) {
                        try {
                            int auto = device.DeviceInfo[i].AutoOn;
                            if (auto == 1) {
                            }
                            nBioBSP.OpenDevice(device.DeviceInfo[i].NameID, device.DeviceInfo[i].Instance);
                        } catch (Exception e) {
                            
                        }
                    }   NBioBSPJNI.FIR_HANDLE hFIR = nBioBSP.new FIR_HANDLE();
                    nBioBSP.Enroll(hFIR, null);
                    if (checkError()) {
                        if (nBioBSP.GetErrorCode() == NBioBSPJNI.ERROR.NBioAPIERROR_FUNCTION_FAIL) {
                            nBioBSP.Capture(hFIR);
                    }
                    }   NBioBSPJNI.INPUT_FIR inputFIR = nBioBSP.new INPUT_FIR();
                    NBioBSPJNI.IndexSearch.SAMPLE_INFO sampleInfo = indexSearch.new SAMPLE_INFO();
                    inputFIR.SetFIRHandle(hFIR);
                    NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = nBioBSP.new FIR_TEXTENCODE();
                    //Pega a string de caracteres a partir do handle capturado
                    //Declara o input_fir para fazer a comparação da digital
                    inputFIR.SetTextFIR(textSavedFIR);
                    NBioBSPJNI.IndexSearch.FP_INFO fpInfo = indexSearch.new FP_INFO();
                    /* Faz o processo de identificação:
                    * - Primeiro parametro: digital capturada no momento
                    * - Segundo parametro: nivel de segurança. varia de 1 a 9. 5 é default.
                    * - Terceiro parametro: informação do usuário
                    * */
                    indexSearch.Identify(inputFIR, 5, fpInfo);
                    indexSearch.AddFIR(inputFIR, userID, sampleInfo);
                    if (checkError()) {
                    return 0;
                    }   this.digitalCapturada = nBioBSP.GetTextFIRFromHandle(hFIR, textSavedFIR);
                    this.digitalCapturadaString = textSavedFIR.TextFIR;
                    nBioBSP.CloseDevice(device.DeviceInfo[0].NameID, device.DeviceInfo[0].Instance);
                    if (this.digitalCapturadaString == null) {
                        biometria.setAtivo(false);
                    dao.update(biometria, true);
                    return 2;
                    }   biometria.setBiometria(digitalCapturadaString);
                    if (!biometria.isAtivo()) {
                        Object[] options = {"Sim", "Não"};
                        int resposta = JOptionPane.showOptionDialog(null,
                                "Tem certeza que reativar esta biometria?", "Mensagem do Programa",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                options, options[0]);
                        if (resposta == 0) {
                            List list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(MacFilial.getAcessoFilial().getId());
                            if (!list.isEmpty()) {
                                biometria.setAtivo(true);
                                if (!dao.update(biometria, true)) {
                                    return 0;
                                }
                            }
                            // OPERAÇÃO CANCELADA PELO USUÁRIO - SERVIDOR
                            return 4;
                            
                        }
                    } else {
                        if (!dao.update(biometria, true)) {
                            return 0;
                        }
                    }   Close.clear();
                    hFIR.dispose();
                    hFIR = null;
                    break;
                case "topdata":
                    StringBuilder digital1 = new StringBuilder();
                    StringBuilder digital2 = new StringBuilder();
                    Boolean digital_capturada_1 = false;
                    Boolean digital_capturada_2 = false;
                    while (digital_capturada_1 == false || digital_capturada_2 == false) {
                        try {
                            if (!digital_capturada_1) {
                                JOptionPane.showMessageDialog(null, "Coloque a PRIMEIRA Digital");
                                digital1 = getDigitalHamster();
                                digital_capturada_1 = true;
                            }
                            if (!digital_capturada_2) {
                                JOptionPane.showMessageDialog(null, "Coloque a SEGUNDA Digital");
                                digital2 = getDigitalHamster();
                                digital_capturada_2 = true;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                        
                        if (!digital_capturada_1 || !digital_capturada_2) {
                            return 0;
                        }
                    }   if (digital1.toString().isEmpty() || digital2.toString().isEmpty()) {
                        biometria.setAtivo(false);
                        biometria.setEnviado(true);
                        dao.update(biometria, true);
                        return 2;
                    }
                    biometria.setBiometria(digital1.toString());
                    biometria.setBiometria2(digital2.toString());
                    biometria.setEnviado(false);
                    if (!biometria.isAtivo()) {
                        Object[] options = {"Sim", "Não"};
                        int resposta = JOptionPane.showOptionDialog(null,
                                "Tem certeza que reativar esta biometria?", "Mensagem do Programa",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                options, options[0]);
                        if (resposta == 0) {
                            List list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(MacFilial.getAcessoFilial().getId());
                            if (!list.isEmpty()) {
                                biometria.setAtivo(true);
                                if (!dao.update(biometria, true)) {
                                    return 0;
                                }
                        }
                        // OPERAÇÃO CANCELADA PELO USUÁRIO - SERVIDOR
                        return 4;

                    }
                } else {
                    if (!dao.update(biometria, true)) {
                        return 0;
                    }
                }   break;
            }

        } else {
            return 3;
        }

        return 1;
    }

    public StringBuilder getDigitalHamster() throws Exception {

        nBioBSP = new NBioBSPJNI();
        NBioBSPJNI.Export exportEngine = nBioBSP.new Export();
        NBioBSPJNI.FIR_HANDLE hSavedFIR = nBioBSP.new FIR_HANDLE();
        NBioBSPJNI.Export.DATA exportData = exportEngine.new DATA();
        NBioBSPJNI.WINDOW_OPTION winOption = nBioBSP.new WINDOW_OPTION();
        NBioBSPJNI.INPUT_FIR inputFIR = nBioBSP.new INPUT_FIR();
        StringBuilder digitalCapturada = new StringBuilder();

        try {

            nBioBSP.OpenDevice();
            //nBioBSP.Capture(NBioBSPJNI.FIR_PURPOSE.VERIFY, hSavedFIR, 5000, null, winOption);
            nBioBSP.Capture(hSavedFIR);
            nBioBSP.CloseDevice();
            if (nBioBSP.IsErrorOccured() == false) {
                inputFIR.SetFIRHandle(hSavedFIR);
                exportEngine.ExportFIR(inputFIR, exportData, NBioBSPJNI.EXPORT_MINCONV_TYPE.FIM01_HV);
                digitalCapturada.append(convertArrayByteToHex(exportData.FingerData[0].Template[0].Data));
            }
        } catch (Exception ex) {
            throw new Exception("Erro ao capturar digital !", ex);
        }
        System.out.println(nBioBSP.GetErrorCode());
        return digitalCapturada;
    }

    public void loadBiometria(List<Biometria> list) {
        if (load) {
            if (hardware) {
                if (!open) {
                    for (int i = 0; i < device.DeviceCount; i++) {
                        try {
                            int auto = device.DeviceInfo[i].AutoOn;
                            if (auto == 1) {
                            }
                            nBioBSP.OpenDevice(device.DeviceInfo[i].NameID, device.DeviceInfo[i].Instance);
                        } catch (Exception e) {

                        }
                    }
                }
                load = false;
                device_code = device.DeviceInfo[0].DeviceID;
                device_name = device.DeviceInfo[0].Name;
                indexSearch.ClearDB();
                for (int i = 0; i < list.size(); i++) {
                    NBioBSPJNI.IndexSearch.SAMPLE_INFO sampleInfo = indexSearch.new SAMPLE_INFO();
                    NBioBSPJNI.INPUT_FIR inputFIR = nBioBSP.new INPUT_FIR();
                    NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = nBioBSP.new FIR_TEXTENCODE();
                    // list.add(i, (Biometria) new Dao().rebind(list.get(i)));
                    textSavedFIR.TextFIR = list.get(i).getBiometria();
                    inputFIR.SetTextFIR(textSavedFIR);
                    indexSearch.AddFIR(inputFIR, list.get(i).getPessoa().getId(), sampleInfo);
                }
                setOpen(true);
            }
        }
    }

    public Map<Integer, Integer> comparer() {
        NBioBSPJNI.FIR_HANDLE fir_handle = nBioBSP.new FIR_HANDLE();
        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = null;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // VERFICICA SE O DEDO ESTA SOBRE O SENSOR
        if (!placed()) {
            //NBioBSPJNI.WINDOW_OPTION option = nBioBSP.new WINDOW_OPTION();
            //option.WindowStyle = NBioBSPJNI.WINDOW_STYLE.INVISIBLE;
            //nBioBSP.Capture(NBioBSPJNI.FIR_PURPOSE.VERIFY, fir_handle, -1, null, option);
            nBioBSP.Capture(fir_handle);
            //Obtem a digital capturada em modo texto
            Integer id = null;
            Integer digitalCapturada = 0;
            String textFir = "";
            if (!nBioBSP.IsErrorOccured()) {
                textSavedFIR = nBioBSP.new FIR_TEXTENCODE();
                digitalCapturada = nBioBSP.GetTextFIRFromHandle(fir_handle, textSavedFIR);
                textFir = textSavedFIR.TextFIR;
                NBioBSPJNI.INPUT_FIR inputFIR = nBioBSP.new INPUT_FIR();
                inputFIR.SetFIRHandle(fir_handle);
                NBioBSPJNI.IndexSearch.FP_INFO fpInfo = indexSearch.new FP_INFO();
                indexSearch.Identify(inputFIR, 6, fpInfo, 5000);

                if (fpInfo.ID > 0) {
                    id = fpInfo.ID;
                    map.put(0, id);
                } else {
                    map.put(1, id);
                }
                return map;
            } else {

            }
        }
        return null;
    }

    public Boolean placed() {
        if (conf.getPlaced()) {
            Boolean fingerPlaced = new Boolean(false);
            nBioBSP.CheckFinger(fingerPlaced);
            return fingerPlaced;
        } else {
            Boolean fingerPlaced = new Boolean(true);
            nBioBSP.CheckFinger(fingerPlaced);
            return fingerPlaced;
        }
    }

    public void dispose() {
        if (indexSearch != null) {
            indexSearch.dispose();
            indexSearch = null;
        }

        if (nBioBSP != null) {
            nBioBSP.CloseDevice();
            nBioBSP.dispose();
            nBioBSP = null;
        }
    }

    public void close() throws Exception {
        if (nBioBSP != null) {
            for (int i = 0; i < device.DeviceCount; i++) {
                try {
                    nBioBSP.CloseDevice(device.DeviceInfo[i].NameID, device.DeviceInfo[i].Instance);
                } catch (Exception e) {

                }
            }
            open = false;
        }
    }

    public NBioBSPJNI.IndexSearch getObjIndex() {
        return indexSearch;
    }

    public void setObjIndex(NBioBSPJNI.IndexSearch objIndex) {
        this.indexSearch = objIndex;
    }

    public Integer getDigitalCapturada() {
        return digitalCapturada;
    }

    public void setDigitalCapturada(Integer digitalCapturada) {
        this.digitalCapturada = digitalCapturada;
    }

    public String getDigitalCapturadaString() {
        return digitalCapturadaString;
    }

    public void setDigitalCapturadaString(String digitalCapturadaString) {
        this.digitalCapturadaString = digitalCapturadaString;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Biometria getBiometria() {
        return biometria;
    }

    public void setBiometria(Biometria biometria) {
        this.biometria = biometria;
    }

    public Boolean checkError() {
        if (nBioBSP.IsErrorOccured()) {
            Integer code = nBioBSP.GetErrorCode();
            // OPERAÇÃO CANCELADA - QUANDO COLHIA A BIOMETRIA
            if (code == 513) {
                BiometriaErroDao biometriaErroDao = new BiometriaErroDao();
                BiometriaErro biometriaErro;
                BiometriaErro be;
                if (conf.getType() == 1) {
                    be = biometriaErroDao.findByDecice(code);
                    if (be != null) {
                        new Dao().delete(be, true);
                    }
                    new Dao().delete(be, true);
                    biometriaErro = new BiometriaErro();
                    biometriaErro.setNrCodigoErro(code);
                    biometriaErro.setNrDispositivo(conf.getDevice());
                    biometriaErro.setMacFilial(null);
                } else if (conf.getType() == 2) {
                    be = biometriaErroDao.findByMac(MacFilial.getAcessoFilial().getId());
                    if (be != null) {
                        new Dao().delete(be, true);
                    }
                    biometriaErro = new BiometriaErro();
                    biometriaErro.setNrCodigoErro(null);
                    biometriaErro.setNrDispositivo(conf.getDevice());
                    biometriaErro.setMacFilial(MacFilial.getAcessoFilial());
                }

            }
            return false;
        }
        return true;
    }

    public NBioBSPJNI getnBioBSPJNI() {
        return nBioBSP;
    }

    public void setnBioBSPJNI(NBioBSPJNI nBioBSPJNI) {
        this.nBioBSP = nBioBSPJNI;
    }

    public Boolean getShowBiometria() {
        return showBiometria;
    }

    public void setShowBiometria(Boolean showBiometria) {
        this.showBiometria = showBiometria;
    }

    public Boolean getLoad() {
        return load;
    }

    public void setLoad(Boolean load) {
        this.load = load;
    }

    public Boolean getHardware() {
        return hardware;
    }

    public void setHardware(Boolean hardware) {
        this.hardware = hardware;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Short getDevice_code() {
        return device_code;
    }

    public void setDevice_code(Short device_code) {
        this.device_code = device_code;
    }

    public Integer getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(Integer deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public Integer getDevice_start() {
        return device_start;
    }

    public void setDevice_start(Integer device_start) {
        this.device_start = device_start;
    }

    // NOVO
    public static StringBuffer convertArrayByteToHex(byte[] P) {
        int iVlrAsc = 0;
        StringBuffer sSaida = new StringBuffer();
        int i = 0;

        for (i = 0; i <= P.length - 1; i++) {
            iVlrAsc = (P[i] < 0 ? (P[i] + 256) : P[i]);

            sSaida.append(completaString(Integer.toHexString(iVlrAsc), 2, "0"));
        }

        return (sSaida);
    }

    public static String completaString(String var1, int Len, String complemento) {
        while (var1.length() < Len) {
            var1 = complemento + var1;
        }
        return (var1);
    }
}
