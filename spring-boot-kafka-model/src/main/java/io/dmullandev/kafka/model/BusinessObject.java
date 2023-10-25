package io.dmullandev.kafka.model;

/**
 * Business object containing businessy stuff!
 * 
 * @author dmullandevv
 *
 */
public class BusinessObject {
    private int businessObjectId;
    private BasicBusinessObjectInformation basicBusinessObjectInformation;

    public BusinessObject() {}

    public BusinessObject(int businessObjectId) {
        this.businessObjectId = businessObjectId;

    }

    /**
     * @return the businessObjectId
     */
    public int getBusinessObjectId() {
        return businessObjectId;
    }

    /**
     * @param businessObjectId the businessObjectId to set
     */
    public void setBusinessObjectId(int businessObjectId) {
        this.businessObjectId = businessObjectId;
    }

    /**
     * @return the basicBusinessObjectInformation
     */
    public BasicBusinessObjectInformation getBasicBusinessObjectInformation() {
        return basicBusinessObjectInformation;
    }

    /**
     * @param basicBusinessObjectInformation the basicBusinessObjectInformation to set
     */
    public void setBasicBusinessObjectInformation(BasicBusinessObjectInformation basicBusinessObjectInformation) {
        this.basicBusinessObjectInformation = basicBusinessObjectInformation;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BusinessObject [businessObjectId=")
               .append(businessObjectId)
               .append(", basicBusinessObjectInformation=")
               .append(basicBusinessObjectInformation)
               .append("]");
        return builder.toString();
    }

}
