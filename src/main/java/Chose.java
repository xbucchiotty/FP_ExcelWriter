import java.util.Date;

/**
 * User: xbucchiotty
 * Date: 27/04/12
 * Time: 08:15
 */
public class Chose {

    private Date prop1;

    private Double prop2;

    private String prop3;

    public Chose(Date prop1, Double prop2, String prop3) {
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.prop3 = prop3;
    }

    public Date getProp1() {
        return prop1;
    }

    public Double getProp2() {
        return prop2;
    }

    public String getProp3() {
        return prop3;
    }
}
