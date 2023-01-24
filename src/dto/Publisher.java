package dto;

import java.io.Serializable;

public class Publisher implements Serializable {
    String pubId, pubName, pubPhoneNumber;

    public Publisher(String pubId, String pubName, String pubPhoneNumber) {
        this.pubId = pubId;
        this.pubName = pubName;
        this.pubPhoneNumber = pubPhoneNumber;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubPhoneNumber() {
        return pubPhoneNumber;
    }

    public void setPubPhoneNumber(String pubPhoneNumber) {
        this.pubPhoneNumber = pubPhoneNumber;
    }
}
