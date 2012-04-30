import org.junit.Test;
import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.excel.ColumnGroup;
import org.xbucchiotty.function.excel.ExcelColumnGroupAgregeur;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

/**
 * User: xbucchiotty
 * Date: 27/04/12
 * Time: 08:11
 */
public class ExcelReducer {

    private static final int INITIAL_ROW = 0;
    private static final int INITIAL_COLUMN = 0;

    @Test
    public void testRefactorOneGroup() {
        Sheet sheet = new Sheet();
        Collection<Chose> choses = getData();

        new ExcelColumnGroupAgregeur<Chose>(choses, sheet, INITIAL_ROW, INITIAL_COLUMN).
                agrege(
                        firstGroup(FirstColumnGroup.VISIBLE)
                )
        ;

        assertThat(sheet.getRender()).isEqualTo("TITRE GROUPE\nDate;Double\n01/02/1904;12.0\n01/02/1905;124.0");
    }

    @Test
    public void testRefactorTwoGroups() {
        Sheet sheet = new Sheet();
        Collection<Chose> choses = getData();

        new ExcelColumnGroupAgregeur<Chose>(choses, sheet, INITIAL_ROW, INITIAL_COLUMN).
                agrege(
                        asList(
                                firstGroup(FirstColumnGroup.VISIBLE),
                                secondGroup()
                        )
                );

        assertThat(sheet.getRender()).isEqualTo("TITRE GROUPE;SECOND_GROUP\nDate;Double;PROP3\n01/02/1904;12.0;Test 1\n01/02/1905;124.0;Test3");
    }

    @Test
    public void testRefactorTwoGroupsWithProp2Hidden() {
        Sheet sheet = new Sheet();
        Collection<Chose> choses = getData();

        new ExcelColumnGroupAgregeur<Chose>(choses, sheet, INITIAL_ROW, INITIAL_COLUMN).
                agrege(
                        asList(
                                firstGroup(FirstColumnGroup.HIDDEN),
                                secondGroup()
                        )
                );

        assertThat(sheet.getRender()).isEqualTo("TITRE GROUPE;SECOND_GROUP\nDate;PROP3\n01/02/1904;Test 1\n01/02/1905;Test3");
    }

    private List<Chose> getData() {
        return asList(new Chose(new Date(4, 1, 1, 1, 1, 1), 12d, "Test 1"), new Chose(new Date(5, 1, 1, 1, 1, 1), 124d, "Test3"));
    }

    private static ColumnGroup<Chose> firstGroup(boolean prop2Visible) {
        return new FirstColumnGroup(prop2Visible);
    }

    private static ColumnGroup<Chose> secondGroup() {
        return new SecondColumnGroup();
    }


}
