import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LogicImpl implements Logic{

    private final Map<Pair<Integer,Integer>,Boolean> map;
    private int size;

    public LogicImpl(final Set<Pair<Integer,Integer>> set, final int size){
        this.map = new HashMap<>();
        set.forEach(i -> this.map.put(i, false));
        this.size = size;
    }


    @Override
    public Boolean getStatus(Pair<Integer, Integer> pair) {
        return this.map.get(pair);
    }

    @Override
    public void setStatus(Pair<Integer, Integer> pair) {
        if(getStatus(pair) == true){
            this.map.put(pair,false);
        }else{
            this.map.put(pair,true);
        }
    }

    @Override
    public Optional<List<List<Pair<Integer,Integer>>>> checkWin() {
        final int X_START = 0;
        final int Y_START = 0;
        int x;
        int y;
        int counter = 0;
        List<List<Pair<Integer,Integer>>> celssDisabled = new ArrayList<>();
        for(int i = 0; i<this.size; i++){
            List<Pair<Integer,Integer>> diagonalList = new ArrayList<>();
            x = X_START;
            y = i;
            while(x<size && y<size){
                System.out.println("Analizzando posizione X:"+x+" Y:"+y);
                var pair = new Pair<Integer,Integer>(x,y);
                if(getStatus(pair)){
                    diagonalList.add(pair);
                    counter++;
                    System.out.println("Counter aggiornato:"+counter);
                }
                x++;
                y++;
            }
            if(counter == 3){
                celssDisabled.add(diagonalList);
            }
            counter = 0;
        }
        for(int i = 1; i<this.size; i++){
            List<Pair<Integer,Integer>> diagonalList = new ArrayList<>();
            x = i;
            y = Y_START;
            while(x<size && y<size){
                System.out.println("Analizzando posizione X:"+x+" Y:"+y);
                var pair = new Pair<Integer,Integer>(x,y);
                if(getStatus(pair)){
                    diagonalList.add(pair);
                    counter++;
                    System.out.println("Counter aggiornato:"+counter);
                }
                x++;
                y++;
            }
            if(counter == 3){
                celssDisabled.add(diagonalList);
            }
            counter = 0;
        }
        System.out.println(celssDisabled);
        return Optional.of(celssDisabled);
    }
    
}
