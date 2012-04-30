import org.junit.Test;
import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.excel.Column;
import org.xbucchiotty.function.excel.ExcelAgregeur;

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

    @Test
    public void testRefactor() {
        Sheet sheet = new Sheet();

        Collection<Chose> choses = asList(new Chose(new Date(4, 1, 1, 1, 1, 1), 12d), new Chose(new Date(5, 1, 1, 1, 1, 1), 124d));

        List<Column<Chose, ?>> columns = asList((Column<Chose, ?>)
                date(),
                aDouble()
        );

        int row = 0;
        int col = 0;
        setCellValue(sheet, row++, col, "TITRE GROUPE");

        ExcelAgregeur<Chose> excelWriter = new ExcelAgregeur<Chose>(columns, sheet, row++, 0);
        excelWriter.agrege(choses);

        for (Chose chose : choses) {
            col = 0;
            setCellValue(sheet, row, col++, chose.getProp1());
            setCellValue(sheet, row, col, chose.getProp2());
            row++;
        }

        assertThat(sheet.getRender()).isEqualTo("TITRE GROUPE\nDate;Double\n01/02/1904;12.0\n01/02/1905;124.0");
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
