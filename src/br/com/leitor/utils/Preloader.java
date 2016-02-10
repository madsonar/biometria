package br.com.leitor.utils;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Preloader {

    public JFrame frame;
    public ImageIcon icon;
    public ImageIcon frameIcon;
    public String appTitle;
    public String appStatus;
    public String appIcon;
    private String appFrameIcon;
    // ESPERAR AO INICIAR
    public Boolean waitingStarted;
    public Integer frame_w;
    public Integer frame_h;
    public Boolean showIcon;
    private Boolean minModal;
    private Boolean topFrame;

    public Preloader() {
        frame = new JFrame("Iniciando a aplicação");
        appIcon = "ajax-loader.gif";
        appFrameIcon = "";
        frameIcon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + "frame_icon.png");
        icon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + appIcon);
        appTitle = "Aplicativo";
        appStatus = "loading...";
        // ESPERAR AO INICIAR
        waitingStarted = false;
        frame_w = 500;
        frame_h = 100;
        showIcon = true;
        minModal = false;
        topFrame = false;
    }

    public void show() {
        show(appTitle);
    }

    public void show(String message) {
        try {
            if (appFrameIcon.isEmpty()) {
                frameIcon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + "frame_icon.png");
            } else {
                frameIcon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + appFrameIcon);
                appFrameIcon = "";
            }
            frame = new JFrame("Iniciando a aplicação");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // CENTRALIZADO
            // frame.setLocationRelativeTo(null);
            frame.setIconImage(frameIcon.getImage());
            // frame.setBackground(Color.WHITE);
            frame.setResizable(false);
            // float opacity = 1.0f;
            // frame.setOpacity(opacity);
            // frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            if (showIcon) {
                icon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + appIcon);
            }
            frame.setTitle(appTitle);
            frame.add(new JLabel(appStatus, icon, JLabel.CENTER));
            if (minModal) {
                frame.setSize(frame_w, 25);
            } else {
                frame.setSize(frame_w, frame_h);
            }
            if (topFrame) {
                frame.setAlwaysOnTop(true);
            }
            frame.setVisible(true);
            if (waitingStarted) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {

                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void reloadStatus(String status) {
        try {

            frame.setVisible(false);
            if (appFrameIcon.isEmpty()) {
                frameIcon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + "frame_icon.png");
            } else {
                frameIcon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + appFrameIcon);
                appFrameIcon = "";
            }
            frame = new JFrame("Iniciando a aplicação");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // CENTRALIZADO
            // frame.setLocationRelativeTo(null);
            frame.setIconImage(frameIcon.getImage());
            frame.setBackground(Color.WHITE);
            frame.setResizable(false);
            // float opacity = 1.0f;
            // frame.setOpacity(opacity);
            // frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setTitle(appTitle);
            if (minModal) {
                frame.setSize(frame_w, 25);
            } else {
                frame.setSize(frame_w, frame_h);
            }
            if (showIcon) {
                icon = new ImageIcon(Path.getRealPath() + "\\src\\resources\\images\\" + appIcon);
            }
            frame.add(new JLabel(status, icon, JLabel.CENTER));
            if (topFrame) {
                frame.setAlwaysOnTop(true);
            }
            frame.setVisible(true);
            if (waitingStarted) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {

                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void hide() {
        frame.setVisible(false);
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public Boolean getWaitingStarted() {
        return waitingStarted;
    }

    public void setWaitingStarted(Boolean waitingStarted) {
        this.waitingStarted = waitingStarted;
    }

    public Integer getFrame_w() {
        return frame_w;
    }

    public void setFrame_w(Integer frame_w) {
        this.frame_w = frame_w;
    }

    public Integer getFrame_h() {
        return frame_h;
    }

    public void setFrame_h(Integer frame_h) {
        this.frame_h = frame_h;
    }

    public Boolean getShowIcon() {
        return showIcon;
    }

    public void setShowIcon(Boolean showIcon) {
        this.showIcon = showIcon;
    }

    public Boolean getMinModal() {
        return minModal;
    }

    public void setMinModal(Boolean minModal) {
        this.minModal = minModal;
    }

    public String getAppFrameIcon() {
        return appFrameIcon;
    }

    public void setAppFrameIcon(String appFrameIcon) {
        this.appFrameIcon = appFrameIcon;
    }

}
