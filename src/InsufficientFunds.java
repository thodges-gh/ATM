import javax.swing.*;

/**
 * Created by Thomas Hodges on 9/9/2015.
 * Project: HodgesCMIS242Project2
 * Filename: InsufficientFunds.java
 *
 * Course: CMIS 242
 * Professor: Ioan Salomie
 * Assignment: Project 2
 *
 * Platform: Windows 10, IntelliJ IDEA 14.1.4
 * Compiler: jdk1.8.0_45
 */
public class InsufficientFunds extends Exception {

    public InsufficientFunds() {
        JOptionPane frame = new JOptionPane();
        JOptionPane.showMessageDialog(frame, "Insufficient Funds!");
    }
}
