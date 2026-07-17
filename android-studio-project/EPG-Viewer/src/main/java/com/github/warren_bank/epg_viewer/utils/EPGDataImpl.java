package com.github.warren_bank.epg_viewer.utils;

import com.google.common.collect.Lists;

import se.kmdev.tvepg.epg.EPGData;
import se.kmdev.tvepg.epg.domain.EPGChannel;
import se.kmdev.tvepg.epg.domain.EPGEvent;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class EPGDataImpl implements EPGData {
    private Map<EPGChannel, List<EPGEvent>> data;

    private List<EPGChannel> allChannels;
    private List<EPGChannel> filteredChannels;

    public EPGDataImpl(Map<EPGChannel, List<EPGEvent>> data) {
        this.data = data;

        if (data != null) {
            allChannels = Lists.newArrayList(data.keySet());
            Collections.sort(allChannels, alphabeticOrderComparator);
        }
        else {
            allChannels = Lists.newArrayList();
        }

        filterChannels(null, false);
    }

    public void filterChannels(String constraint, boolean caseSensitive) {
        if ((constraint == null) || constraint.isEmpty()) {
            filteredChannels = Lists.newArrayList(allChannels);
        }
        else {
            filteredChannels = Lists.newArrayList();

            if (!caseSensitive) constraint = constraint.toLowerCase();

            for (EPGChannel channel : allChannels) {
                String name = channel.getName();
                if (!caseSensitive) name = name.toLowerCase();

                if (name.contains(constraint))
                    filteredChannels.add(channel);
            }
        }
    }

    @Override
    public EPGChannel getChannel(int channelPosition) {
        try {
            return filteredChannels.get(channelPosition);
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<EPGEvent> getEvents(int channelPosition) {
        EPGChannel channel = getChannel(channelPosition);
        return ((channel != null) && (data != null))
            ? data.get(channel)
            : Lists.newArrayList();
    }

    @Override
    public EPGEvent getEvent(int channelPosition, int programPosition) {
        try {
            return getEvents(channelPosition).get(programPosition);
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public int getChannelCount() {
        return filteredChannels.size();
    }

    @Override
    public boolean hasData() {
        return !filteredChannels.isEmpty();
    }

    // Comparator static class

    private static class AlphabeticOrderComparator implements Comparator<EPGChannel> {
        @Override
        public int compare(EPGChannel a, EPGChannel b) {
            if ((a == null) || (b == null)) throw new NullPointerException();

            return a.getName().compareTo(b.getName());
        }
    }

    // Comparator static instances

    private static final AlphabeticOrderComparator alphabeticOrderComparator = new AlphabeticOrderComparator();
}
