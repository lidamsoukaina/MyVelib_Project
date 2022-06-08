package core.classes;
import java.util.Comparator;

/**
 * Class for stations sorting, stations are sorted w.r.t. the difference
 * between the number of dropping and renting operations.
 * @version 1.0
 */
public class SortingStationsByLeastOccupied implements Comparator<Station> {
    @Override
    public int compare(Station o1, Station o2) {
        return (o2.getTotalDrops() - o2.getTotalRents()) - (o1.getTotalDrops() - o1.getTotalRents());
    }
}