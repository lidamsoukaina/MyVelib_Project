package core.classes;
import java.util.Comparator;
/**
 * Class for stations sorting based on  the total number of renting + dropping operations
 * @version 1.0
 */
public class SortingStationsByMostUsed implements Comparator<Station> {
    @Override
    public int compare(Station o1, Station o2) {
        return -o1.getTotalDrops() - o1.getTotalRents() + o2.getTotalDrops() + o2.getTotalRents();
    }
}