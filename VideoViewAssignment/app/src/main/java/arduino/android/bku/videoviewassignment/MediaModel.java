package arduino.android.bku.videoviewassignment;

public class MediaModel {
    private String mediaId = "";
    private String mediaThumbnail = "";
    private String mediaTitle = "";
    private String mediaInfo = "";
    private String path = "";

    public String getMediaId() {return mediaId; }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaThumbnail() {
        return mediaThumbnail;
    }

    public void setMediaThumbnail(String mediaThumbnail) {
        this.mediaThumbnail = mediaThumbnail;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getMediaInfo() {
        return mediaInfo;
    }

    public void setMediaInfo(String mediaInfo) {
        this.mediaInfo = mediaInfo;
    }

    public void setPath(String path) { this.path = path; }

    public String getPath() {return path; }
}