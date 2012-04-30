import org.junit.Test;
import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.excel.Column;
import org.xbucchiotty.function.excel.ColumnGroup;
import org.xbucchiotty.function.excel.ExcelColumnAgregeur;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.xbucchiotty.excel.Sheet.setCellValue;

/**
 * User: xbucchiotty
 * Date: 27/04/12
 * Time: 08:11
 */
public class ExcelReducer {

    private static final int INTIAL_ROW = 0;
    private static final int INITIAL_COLUMN = 0;

    @Test
    public void testRefactor() {
        Sheet sheet = extract();

        assertThat(sheet.getRender()).isEqualTo("TITRE GROUPE\nDate;Double\n01/02/1904;12.0\n01/02/1905;124.0");
    }

    private Sheet extract() {
        Sheet sheet = new Sheet();
        Collection<Chose> choses = getData();

        int row = INTIAL_ROW;

        setCellValue(sheet, row++, INITIAL_COLUMN, "TITRE GROUPE");

        new ExcelColumnAgregeur<Chose>(
                choses,
                sheet,
                row,
                INITIAL_COLUMN
        ).agrege(getColumnGroup().getColumns());

        return sheet;
    }

    private List<Chose> getData() {
        return asList(new Chose(new Date(4, 1, 1, 1, 1, 1), 12d), new Chose(new Date(5, 1, 1, 1, 1, 1), 124d));
    }

    private ColumnGroup<Chose> getColumnGroup() {
        return new ColumnGroup<Chose>() {
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
        };
    }


}
