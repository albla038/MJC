package albert.lannerstrom;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class MultiJob {

    private int number;
    private final String fileName;
    private final Boolean extraGroup;
    private final Element table0;
    private Element table1;
    private Element table2;
    private final ArrayList<Component> group0;
    private ArrayList<Component> group1;
    private ArrayList<Component> group2;

    public MultiJob(String fileName, Boolean extraGroup, Element firstTable, ArrayList<Component> firstGroup) {
        this.fileName = fileName;
        this.extraGroup = extraGroup;
        this.table0 = firstTable;
        this.group0 = firstGroup;
        createNumber();
    }

    public MultiJob(String fileName, Boolean extraGroup, Element firstTable, Element secondTable, ArrayList<Component> firstGroup, ArrayList<Component> secondGroup) {
        this.fileName = fileName;
        this.extraGroup = extraGroup;
        this.table0 = firstTable;
        this.table1 = secondTable;
        this.group0 = firstGroup;
        this.group1 = secondGroup;
        createNumber();
    }

    public MultiJob(String fileName, Boolean extraGroup, Element firstTable, Element secondTable, Element thirdTable, ArrayList<Component> firstGroup, ArrayList<Component> secondGroup, ArrayList<Component> thirdGroup) {
        this.fileName = fileName;
        this.extraGroup = extraGroup;
        this.table0 = firstTable;
        this.table1 = secondTable;
        this.table2 = thirdTable;
        this.group0 = firstGroup;
        this.group1 = secondGroup;
        this.group2 = thirdGroup;
        createNumber();
    }

    private void createNumber() {
        String str = getFileName();
        str = str.replaceAll("[^\\d]", "");
        this.number = Integer.parseInt(str);
    }

    public int getNumber() {
        return number;
    }

    public String getFileName() {
        return fileName;
    }

    public Boolean hasExtraGroup() {
        return extraGroup;
    }

    public Element getTable0() {
        return table0;
    }

    public Element getTable1() {
        return table1;
    }

    public ArrayList<Component> getGroup0() {
        return group0;
    }

    public ArrayList<Component> getGroup1() {
        return group1;
    }
}
