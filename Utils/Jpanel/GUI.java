package Utils.Jpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    static public void menuPresentation() {
        JFrame frame = new JFrame("Welcome!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 300);
        frame.setLayout(new BorderLayout());
        JButton botaoOK = new JButton("Seguir");
//        botaoOK.setPreferredSize(new Dimension(120));
        botaoOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JPanel painelMain = new JPanel();

        painelMain.setBackground(Color.DARK_GRAY);
        JLabel labelName = new JLabel("Historia do Kujikun da Silva Oliveira");
        JLabel labelPresentation = new JLabel(
                "<html>" +
                        "<p>Kujikun fugiu da coreia do norte tenta uma vida de cantor.<br>" +
                        "Veio pra o Brasil, fez um passaporte falso, e se tornou Kujikun da Silva Oliveira.<br>" +
                        "Tentou vira MC de Rap, mas era péssimo canto.<br>" +
                        "Se apaixonou por melissa, que estudava programação.<br>" +
                        "E assim entrou na área de TI e virou programador.<br><p>" +
                        "</html>"
        );
        labelName.setForeground(Color.white);
        labelName.setFont(new Font("Arial", Font.PLAIN, 20));

        labelPresentation.setForeground(Color.white);
        labelPresentation.setFont(new Font("Arial", Font.PLAIN, 16));

        painelMain.add(labelName);
        painelMain.add(labelPresentation);
        painelMain.add(botaoOK);
        frame.add(painelMain, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    void Menuinformations(int yearCurrent, String occupation, double xp, int network){
        System.out.printf("Ano: %d | Ocupacao Atual: %s | XP: %.2f | NetWork: %d\n", yearCurrent, occupation, xp, network);
    }

    void MainMenu(){

    }

}
