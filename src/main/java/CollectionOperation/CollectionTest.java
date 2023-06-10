package CollectionOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionTest {
    List<ComparableExtension> dataToCompare = new ArrayList<>();

    public void sortData() {
        Collections.sort(dataToCompare, new CustomComparator());
    }
}
