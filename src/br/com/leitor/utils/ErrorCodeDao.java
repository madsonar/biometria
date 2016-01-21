package br.com.leitor.utils;

import javax.persistence.PersistenceException;
import oracle.toplink.essentials.exceptions.DatabaseException;
import oracle.toplink.essentials.exceptions.TopLinkException;

public class ErrorCodeDao {

    private String simpleMessage;
    private String advancedMessage;

    public ErrorCodeDao() {
        this.simpleMessage = "";
        this.advancedMessage = "";
    }

    public ErrorCodeDao(String simpleMessage, String advancedMessage) {
        this.simpleMessage = simpleMessage;
        this.advancedMessage = advancedMessage;
    }

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    public String getAdvancedMessage() {
        return advancedMessage;
    }

    public void setAdvancedMessage(String advancedMessage) {
        this.advancedMessage = advancedMessage;
    }

    public static ErrorCodeDao exceptionMessage(Exception e) {
        ErrorCodeDao errorCodeDao = new ErrorCodeDao();
        errorCodeDao.simpleMessage = e.getCause().getCause().getMessage().replace("ERROR:", "");
        errorCodeDao.advancedMessage = e.getMessage();
        return errorCodeDao;
    }

    public static ErrorCodeDao persistenceExceptionMessage(PersistenceException e) {
        ErrorCodeDao errorCodeDao = new ErrorCodeDao();
        errorCodeDao.simpleMessage = e.getCause().getCause().getMessage().replace("ERROR:", "");
        errorCodeDao.advancedMessage = e.getMessage();
        return errorCodeDao;
    }

    public static ErrorCodeDao databaseExceptionMessage(DatabaseException e) {
        ErrorCodeDao errorCodeDao = new ErrorCodeDao();
        errorCodeDao.simpleMessage = e.getCause().getCause().getMessage().replace("ERROR:", "");
        errorCodeDao.advancedMessage = e.getMessage();
        return errorCodeDao;
    }

    public static ErrorCodeDao toplinkExceptionMessage(TopLinkException e) {
        ErrorCodeDao errorCodeDao = new ErrorCodeDao();
        errorCodeDao.simpleMessage = e.getCause().getCause().getMessage().replace("ERROR:", "");
        errorCodeDao.advancedMessage = e.getMessage();
        return errorCodeDao;
    }

}
