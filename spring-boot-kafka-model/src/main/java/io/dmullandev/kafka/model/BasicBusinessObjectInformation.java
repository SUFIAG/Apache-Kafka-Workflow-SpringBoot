package io.dmullandev.kafka.model;

/**
 * Some basic information identifying an object
 * 
 * @author dmullandev
 *
 */
public class BasicBusinessObjectInformation {
    private String objectType;
    private String objectDescription;

    public BasicBusinessObjectInformation() {

    }

    public BasicBusinessObjectInformation(String objectType, String objectDescription) {
        this.objectType = objectType;
        this.objectDescription = objectDescription;
    }

    /**
     * @return the objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * @return the objectDescription
     */
    public String getObjectDescription() {
        return objectDescription;
    }

    /**
     * @param objectDescription the objectDescription to set
     */
    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BasicBusinessObjectInformation [objectType=")
               .append(objectType)
               .append(", objectDescription=")
               .append(objectDescription)
               .append("]");
        return builder.toString();
    }

}
