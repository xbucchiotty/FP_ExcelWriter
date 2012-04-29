package org.xbucchiotty.excel;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: xbucchiotty
 * Date: 27/04/12
 * Time: 08:11
 */
public class Sheet {

    Map<Key, Object> cells = new LinkedHashMap<Key, Object>();

    public static void setCellValue(Sheet sheet, int row, int column, Date value) {
        sheet.cells.put(Key.getKey(row, column), new SimpleDateFormat("dd/MM/yyyy").format(value));
    }

    public static void setCellValue(Sheet sheet, int row, int column, Double value) {
        sheet.cells.put(Key.getKey(row, column), Double.toString(value));
    }

    public static void setCellValue(Sheet sheet, int row, int column, String value) {
        sheet.cells.put(Key.getKey(row, column), value);
    }

    public String getRender() {
        int row = 0;
        StringBuilder builder = new StringBuilder();
        for (Key key : cells.keySet()) {
            if (row != key.x) {
                builder.append('\n');
            } else {
                if (!Key.getKey(0, 0).equals(key)) {
                    builder.append(';');
                }
            }
            row = key.x;
            builder.append(cells.get(key));
        }

        return builder.toString();
    }


    private static class Key implements Comparable<Key> {
        Integer x;
        Integer y;

        private Key(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private static Key getKey(int x, int y) {
            return new Key(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            return x.equals(key.x) && y.equals(key.y);
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public int compareTo(Key key) {
            int comparaisonX = x.compareTo(key.x);
            if (comparaisonX == 0) {
                return y.compareTo(key.y);
            }
            return comparaisonX;
        }
    }
}
