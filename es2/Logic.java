import java.util.List;
import java.util.Optional;

public interface Logic {

    public Boolean getStatus(Pair<Integer,Integer> pair);

    public void setStatus(Pair<Integer,Integer> pair);

    public Optional<List<List<Pair<Integer,Integer>>>> checkWin();

}
