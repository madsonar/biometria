package br.com.leitor.utils;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class PreloaderDialog extends JDialog {
    
    String text;
    JDialog dialog;
    
    public PreloaderDialog() {
        initialize();
    }
    
    public final void initialize() {
        dialog = new JDialog();
        dialog.setSize(300, 50);
        dialog.setLocationRelativeTo(null);
    }
    
    @Override
    public void hide() {
        dialog.setModal(false);
        dialog.setVisible(false);
        dialog.dispose();
        dialog = null;
    }
    
    @Override
    public void show() {
        dialog.add(new JLabel(text));
        dialog.setModal(true);
        dialog.setVisible(true);
    }
    
    public void show(String message) {
        dialog.setTitle(message);
        dialog.add(new JLabel(text));
        dialog.setModal(true);
        dialog.setVisible(true);
    }
    
}
