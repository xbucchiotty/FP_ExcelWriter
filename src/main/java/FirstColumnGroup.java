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
    public static final boolean VISIBLE = true;
    public static final boolean HIDDEN = false;

    private final boolean prop2Visible;

    public FirstColumnGroup(boolean prop2Visible) {
        this.prop2Visible = prop2Visible;
    }

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

            @Override
            public boolean isVisible() {
                return true;
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

            @Override
            public boolean isVisible() {
                return prop2Visible;
            }
        };
    }
}
