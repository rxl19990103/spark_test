package etl2.domain;

public class ELementSummary {
    /**
     * {"code":"1","name":"温度","unitCode":"℃","unitName":"摄氏度","valueType":3}
     * <p>
     * <p>
     * <p>
     * {"nodeID":"7b48474b-2c51-4189-9488-502f874ab7c0",
     * "operateTime":"2021/3/24 18:34:20",
     * "nodeName":"采摘",
     * "blockNo":"2632562",
     * "nodeHash":"F134FBF4674B5055D3827053538FF5ADDBDB2AC1",
     * "modifyTime":"2021-04-20T18:51:50Z",
     * "blockHash":"0x50976373d7ed7b148e06cdadada99f8c50f95e1ff5f94967d0261eeea60e26b2",
     * "sourceHash":"3DA30F39D1A4D3894164DA99EA197C1F5C7CA410",
     * "transactionTime":"1618915909904400848",
     * "createTime":"2021-04-20T18:51:51Z"}
     */

    private String nodeID;

    private String operateTime;

    private String nodeName;

    private String blockNo;

    private String nodeHash;

    private String modifyTime;

    private String blockHash;

    private String sourceHash;

    private String transactionTime;

    private String createTime;


    @Override
    public String toString() {
        return "ElementSummary{" +
                "nodeID='" + nodeID + '\'' +
                ", operateTime='" + operateTime + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", blockNo='" + blockNo + '\'' +
                ", nodeHash='" + nodeHash + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", sourceHash='" + sourceHash + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(String blockNo) {
        this.blockNo = blockNo;
    }

    public String getNodeHash() {
        return nodeHash;
    }

    public void setNodeHash(String nodeHash) {
        this.nodeHash = nodeHash;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getSourceHash() {
        return sourceHash;
    }

    public void setSourceHash(String sourceHash) {
        this.sourceHash = sourceHash;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

