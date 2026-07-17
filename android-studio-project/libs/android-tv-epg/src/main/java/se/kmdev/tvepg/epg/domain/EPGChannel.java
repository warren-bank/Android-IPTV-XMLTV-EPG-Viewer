package se.kmdev.tvepg.epg.domain;

/**
 * Created by Kristoffer.
 */
public class EPGChannel {

    private String channelID;
    private String name;
    private String imageURL;

    public EPGChannel(String channelID, String name, String imageURL) {
        this.channelID = channelID;
        this.name = name;
        this.imageURL = imageURL;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
}
