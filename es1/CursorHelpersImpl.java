import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers {

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return new Cursor<X>() {
            private static final int START = 0;
            private List<X> elements = list;
            private X element = list.get(START);

            @Override
            public X getElement() {
                return this.element;
            }

            @Override
            public boolean advance() {
                try{
                    if(elements.get(elements.indexOf(element) + 1) != null){
                        this.element = elements.get(elements.indexOf(element) + 1);
                        return true;
                    }else{
                        return false;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println(e);
                    return false;
                }
            }
            
        };
    }

    @Override
    public Cursor<Integer> naturals() {
        return new Cursor<Integer>() {
            private int element = 0;

            @Override
            public Integer getElement() {
                return this.element;
            }

            @Override
            public boolean advance() {
                this.element++;
                return true;
            }
            
        };
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        return new Cursor<X>() {
            private int elementsSeen = 0;

            @Override
            public X getElement() {
                this.elementsSeen++;
                return input.getElement();
            }

            @Override
            public boolean advance() {
                if(input.advance() && elementsSeen < max){
                    return true;
                }else{
                    return false;
                }
            }
            
        };
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forEach'");
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> newList = new ArrayList<>();
        Cursor<X> newCursor = take(input, max);
        newList.add(newCursor.getElement());
        while(newCursor.advance()){
            newList.add(newCursor.getElement());
        }
        return newList;
    }

}
