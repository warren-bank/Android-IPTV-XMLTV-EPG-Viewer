package se.kmdev.tvepg.epg.domain;

/**
 * Created by Kristoffer.
 */
public class EPGEvent {

    private long start;
    private long end;
    private String title;

    public EPGEvent(long start, long end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCurrent() {
        long now = System.currentTimeMillis();
        return now >= start && now <= end;
    }
}
