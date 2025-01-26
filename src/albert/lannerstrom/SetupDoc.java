package albert.lannerstrom;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class SetupDoc {

    private final int setupMultiJobNumber;
    private int inlineMultiJobNumber;
    private int teardownMultiJobNumber;

    private ArrayList<Component> teardownMsdList;
    private ArrayList<Component> matchedList;
    private ArrayList<Component> cart1_1List;
    private ArrayList<Component> cart1_2List;
    private ArrayList<Component> cart2_1List;
    private ArrayList<Component> cart2_2List;
    private ArrayList<Component> cart3_1List;
    private ArrayList<Component> cart3_2List;
    private ArrayList<Component> cart4_1List;
    private ArrayList<Component> cart4_2List;
    private ArrayList<Component> trayList;
    private ArrayList<Component> extraList;
    private ArrayList<Component> setupMsdList;

    private Document coverPage;
    private Document teardownMsdPage;
    private Document matchedPage;
    private Document cart1_1Page;
    private Document cart1_2Page;
    private Document cart2_1Page;
    private Document cart2_2Page;
    private Document cart3_1Page;
    private Document cart3_2Page;
    private Document cart4_1Page;
    private Document cart4_2Page;
    private Document trayPage;
    private Document extraPage;
    private Document setupMsdPage;

    public SetupDoc(int nextMultiJobNumber) {
        this.setupMultiJobNumber = nextMultiJobNumber;
    }

    public SetupDoc(int nextMultiJobNumber, int currentMultiJobNumber, int oldMultiJobNumber) {
        this.setupMultiJobNumber = nextMultiJobNumber;
        this.inlineMultiJobNumber = currentMultiJobNumber;
        this.teardownMultiJobNumber = oldMultiJobNumber;
    }

    public int getTeardownMultiJobNumber() {
        return teardownMultiJobNumber;
    }

    public int getInlineMultiJobNumber() {
        return inlineMultiJobNumber;
    }

    public int getSetupMultiJobNumber() {
        return setupMultiJobNumber;
    }

    public ArrayList<Component> getTeardownMsdList() {
        return teardownMsdList;
    }

    public void setTeardownMsdList(ArrayList<Component> teardownMsdList) {
        this.teardownMsdList = teardownMsdList;
    }

    public ArrayList<Component> getMatchedList() {
        return matchedList;
    }

    public void setMatchedList(ArrayList<Component> matchedList) {
        this.matchedList = matchedList;
    }

    public ArrayList<Component> getCart1_1List() {
        return cart1_1List;
    }

    public void setCart1_1List(ArrayList<Component> cart1_1List) {
        this.cart1_1List = cart1_1List;
    }

    public ArrayList<Component> getCart1_2List() {
        return cart1_2List;
    }

    public void setCart1_2List(ArrayList<Component> cart1_2List) {
        this.cart1_2List = cart1_2List;
    }

    public ArrayList<Component> getCart2_1List() {
        return cart2_1List;
    }

    public void setCart2_1List(ArrayList<Component> cart2_1List) {
        this.cart2_1List = cart2_1List;
    }

    public ArrayList<Component> getCart2_2List() {
        return cart2_2List;
    }

    public void setCart2_2List(ArrayList<Component> cart2_2List) {
        this.cart2_2List = cart2_2List;
    }

    public ArrayList<Component> getCart3_1List() {
        return cart3_1List;
    }

    public void setCart3_1List(ArrayList<Component> cart3_1List) {
        this.cart3_1List = cart3_1List;
    }

    public ArrayList<Component> getCart3_2List() {
        return cart3_2List;
    }

    public void setCart3_2List(ArrayList<Component> cart3_2List) {
        this.cart3_2List = cart3_2List;
    }

    public ArrayList<Component> getCart4_1List() {
        return cart4_1List;
    }

    public void setCart4_1List(ArrayList<Component> cart4_1List) {
        this.cart4_1List = cart4_1List;
    }

    public ArrayList<Component> getCart4_2List() {
        return cart4_2List;
    }

    public void setCart4_2List(ArrayList<Component> cart4_2List) {
        this.cart4_2List = cart4_2List;
    }

    public ArrayList<Component> getTrayList() {
        return trayList;
    }

    public void setTrayList(ArrayList<Component> trayList) {
        this.trayList = trayList;
    }

    public ArrayList<Component> getExtraList() {
        return extraList;
    }

    public void setExtraList(ArrayList<Component> extraList) {
        this.extraList = extraList;
    }

    public ArrayList<Component> getSetupMsdList() {
        return setupMsdList;
    }

    public void setSetupMsdList(ArrayList<Component> setupMsdList) {
        this.setupMsdList = setupMsdList;
    }

    public Document getCoverPage() {
        return coverPage;
    }

    public void setCoverPage(Document coverPage) {
        this.coverPage = coverPage;
    }

    public Document getTeardownMsdPage() {
        return teardownMsdPage;
    }

    public void setTeardownMsdPage(Document teardownMsdPage) {
        this.teardownMsdPage = teardownMsdPage;
    }

    public Document getMatchedPage() {
        return matchedPage;
    }

    public void setMatchedPage(Document matchedPage) {
        this.matchedPage = matchedPage;
    }

    public Document getCart1_1Page() {
        return cart1_1Page;
    }

    public void setCart1_1Page(Document cart1_1Page) {
        this.cart1_1Page = cart1_1Page;
    }

    public Document getCart1_2Page() {
        return cart1_2Page;
    }

    public void setCart1_2Page(Document cart1_2Page) {
        this.cart1_2Page = cart1_2Page;
    }

    public Document getCart2_1Page() {
        return cart2_1Page;
    }

    public void setCart2_1Page(Document cart2_1Page) {
        this.cart2_1Page = cart2_1Page;
    }

    public Document getCart2_2Page() {
        return cart2_2Page;
    }

    public void setCart2_2Page(Document cart2_2Page) {
        this.cart2_2Page = cart2_2Page;
    }

    public Document getCart3_1Page() {
        return cart3_1Page;
    }

    public void setCart3_1Page(Document cart3_1Page) {
        this.cart3_1Page = cart3_1Page;
    }

    public Document getCart3_2Page() {
        return cart3_2Page;
    }

    public void setCart3_2Page(Document cart3_2Page) {
        this.cart3_2Page = cart3_2Page;
    }

    public Document getCart4_1Page() {
        return cart4_1Page;
    }

    public void setCart4_1Page(Document cart4_1Page) {
        this.cart4_1Page = cart4_1Page;
    }

    public Document getCart4_2Page() {
        return cart4_2Page;
    }

    public void setCart4_2Page(Document cart4_2Page) {
        this.cart4_2Page = cart4_2Page;
    }

    public Document getTrayPage() {
        return trayPage;
    }

    public void setTrayPage(Document trayPage) {
        this.trayPage = trayPage;
    }

    public Document getExtraPage() {
        return extraPage;
    }

    public void setExtraPage(Document extraPage) {
        this.extraPage = extraPage;
    }

    public Document getSetupMsdPage() {
        return setupMsdPage;
    }

    public void setSetupMsdPage(Document setupMsdPage) {
        this.setupMsdPage = setupMsdPage;
    }
}
