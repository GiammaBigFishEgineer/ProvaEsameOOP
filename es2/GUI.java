import javax.swing.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton,Pair<Integer,Integer>> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton GO = new JButton("Go");
        main.add(BorderLayout.SOUTH, GO);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                logic.setStatus(position);
                if(logic.getStatus(position)){
                    button.setText("*");
                }else{
                    button.setText(" ");
                }
                
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb,new Pair<Integer,Integer>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        Set<Pair<Integer,Integer>> set = new HashSet<>();
        cells.keySet().forEach(i -> set.add(cells.get(i)));
        this.logic = new LogicImpl(set,size);
        GO.addActionListener(event -> {
            if(logic.checkWin().isPresent()){
                /*
                 * logica per disabilitare celle
                 */
                redrawDisable();
            }
        });
        this.setVisible(true);
    }   
    
    private void redrawDisable(){
        System.out.println("Celle disabilitate");
    }
}
