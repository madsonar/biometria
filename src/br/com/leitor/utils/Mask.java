package br.com.leitor.utils;

public class Mask {

    private String getModelo(String label, boolean pesquisaInicial) {
        // Pesquisa Inicial
        String pi = "";
        if (pesquisaInicial) {
            pi = "?";
        }
        if (label.equals("cpf")) {
            return pi + "999.999.999-99";
        } else if (label.equals("cnpj")) {
            return pi + "99.999.999/9999-99";
        } else if (label.equals("telefone")) {
            return pi + "(99) 9999-9999";
        } else if (label.equals("celular")) {
            return "(99) " + pi + "9999-9999?9";
        } else if (label.equals("telefone1")) {
            return "(**) " + pi + "****-*****";
        } else if (label.equals("cep")) {
            return pi + "(99) 99.999-999";
        } else if (label.equals("cei")) {
            return pi + "99.999.99999/99";
        } else {
            return "";
        }
    }

    public static String getMascara(String label) {
        Mask mask = new Mask();
        return mask.getModelo(label, false);
    }

    public static String getMascaraPesquisa(String label, boolean pesquisaInicial) {
        Mask mask = new Mask();
        return mask.getModelo(label, pesquisaInicial);
    }

    public static String cep(final String cep) {
        String cepMask = "";
        if (!cep.equals("")) {
            cepMask = cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8);
        }
        return cepMask;
    }

    public static String cnpj(final String cnpj) {
        String cnpjMask = "";
        try {
            if (!cnpj.equals("")) {
                cnpjMask = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
            }
            return cnpjMask;
        } catch (Exception e) {
            return cnpj;
        }
    }

}
