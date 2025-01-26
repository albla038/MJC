package albert.lannerstrom;

public class Component {

    private final String position;
    private String result;
    private final String partNumber;
    private final String partComment;
    private final String type;
    private final String width;
    private final String feederName;
    private final String qty;
    private Boolean matched;
    private Boolean duplicate;
    private String oldPosition;
    private Boolean extra;
    private String cartNumber;

    public Component(String position, String result, String partNumber, String partComment, String type, String width, String feederName, String qty, Boolean matched, Boolean duplicate, Boolean extra) {
        this.position = position;
        this.result = result;
        this.partNumber = partNumber;
        this.partComment = partComment;
        this.type = type;
        this.width = width;
        this.feederName = feederName;
        this.qty = qty;
        this.matched = matched;
        this.duplicate = duplicate;
        this.extra = extra;
        createCartNumber();
    }

    private void createCartNumber() {
        String number = getPosition().substring(0, 5);
        cartNumber = number;
    }

    public String getPosition() {
        return position;
    }

    public String getResult() {
        return result;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartComment() {
        return partComment;
    }

    public String getType() {
        return type;
    }

    public String getWidth() {
        return width;
    }

    public String getFeederName() {
        return feederName;
    }

    public String getQty() {
        return qty;
    }

    public Boolean isMatched() {
        return matched;
    }

    public Boolean isDuplicate() {
        return duplicate;
    }

    public Boolean isExtra() {
        return extra;
    }

    public String getOldPosition() {
        return oldPosition;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMatched(Boolean matched) {
        this.matched = matched;
    }

    public void setDuplicate(Boolean duplicate) {
        this.duplicate = duplicate;
    }

    public void setOldPosition(String oldPosition) {
        this.oldPosition = oldPosition;
    }

    public void setExtra(Boolean extra) {
        this.extra = extra;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public String toString() {
        return getPartNumber() + " - " + getPosition() + "\n";
    }
}
