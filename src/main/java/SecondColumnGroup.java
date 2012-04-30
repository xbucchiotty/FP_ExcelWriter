import org.xbucchiotty.function.excel.Column;
import org.xbucchiotty.function.excel.ColumnGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 14:04
 */
public class SecondColumnGroup implements ColumnGroup<Chose> {
    @Override
    public String getTitle() {
        return "SECOND_GROUP";
    }

    @Override
    public List<Column<Chose, ?>> getColumns() {
        List<Column<Chose, ?>> columns = new ArrayList<Column<Chose, ?>>();
        columns.add(prop3());
        //CAN'T USE Arrays.asList with compiler while building (but ok with Idea while editing ??)
        return columns;
    }

    private Column<Chose, ?> prop3() {
        return new Column<Chose, String>() {
            @Override
            public String getTitle() {
                return "PROP3";
            }

            @Override
            public String extractData(Chose input) {
                return input.getProp3();
            }
        };
    }
}
