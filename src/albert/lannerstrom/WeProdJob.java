package albert.lannerstrom;

public class WeProdJob {

    private int orderNumber;
    private String articleNumber;
    private int operationNumber;
    private int quantity;
    private String pcbArticleNumber;
    private String pcbStorageLocation;
    private String jobPasteType;
    private String programmedCircuit1;
    private String program1;
    private String programmedCircuit2;
    private String program2;
    private String additionalInfo;
    private String userSuppliedComment;

    public WeProdJob(int orderNumber, String articleNumber, int operationNumber, int quantity, String pcbArticleNumber, String pcbStorageLocation, String jobPasteType, String programmedCircuit1, String program1, String programmedCircuit2, String program2, String additionalInfo, String userSuppliedComment) {
        this.orderNumber = orderNumber;
        this.articleNumber = articleNumber;
        this.operationNumber = operationNumber;
        this.quantity = quantity;
        this.pcbArticleNumber = pcbArticleNumber;
        this.pcbStorageLocation = pcbStorageLocation;
        this.jobPasteType = jobPasteType;
        this.programmedCircuit1 = programmedCircuit1;
        this.program1 = program1;
        this.programmedCircuit2 = programmedCircuit2;
        this.program2 = program2;
        this.additionalInfo = additionalInfo;
        this.userSuppliedComment = userSuppliedComment;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public int getOperationNumber() {
        return operationNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPcbArticleNumber() {
        return pcbArticleNumber;
    }

    public String getPcbStorageLocation() {
        return pcbStorageLocation;
    }

    public String getJobPasteType() {
        return jobPasteType;
    }

    public String getProgrammedCircuit1() {
        return programmedCircuit1;
    }

    public String getProgram1() {
        return program1;
    }

    public String getProgrammedCircuit2() {
        return programmedCircuit2;
    }

    public String getProgram2() {
        return program2;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getUserSuppliedComment() {
        return userSuppliedComment;
    }
}
