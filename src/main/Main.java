/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import core.views.MegaferiaFrame;

/**
 *
 * @author kevin
 */
public class Main {
    
    public static void main(String[] args) {
        // Por ahora es solo un copy paste del psvm de la vista
        // Puede estar dispuesto a cambios -Kevin
        System.setProperty("flatlaf.useNativeLibrary", "false");
        
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MegaferiaFrame().setVisible(true);
            }
        });
    }
}
