import org.xbucchiotty.function.excel.Column;
import org.xbucchiotty.function.excel.ColumnGroup;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

/**
* User: xbucchiotty
* Date: 30/04/12
* Time: 14:03
*/
public class FirstColumnGroup implements ColumnGroup<Chose> {
    @Override
    public String getTitle() {
        return "TITRE GROUPE";
    }

    @Override
    public List<Column<Chose, ?>> getColumns() {
        return asList((Column<Chose, ?>)
                date(),
                aDouble()
        );
    }

    private Column<Chose, Date> date() {
        return new Column<Chose, Date>() {
            @Override
            public String getTitle() {
                return "Date";
            }

            @Override
            public Date extractData(Chose input) {
                return input.getProp1();
            }
        };
    }

    private Column<Chose, Double> aDouble() {
        return new Column<Chose, Double>() {
            @Override
            public String getTitle() {
                return "Double";
            }

            @Override
            public Double extractData(Chose input) {
                return input.getProp2();
            }
        };
    }
}
