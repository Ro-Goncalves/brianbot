package com.rgbrain.brianbot.controller;

import java.util.List;

public class Payload  {   

        private String event;
        private String instance;
        private List<Data> data;
        private String destination;
        private String dateTime;
        private String sender;
        private String serverUrl;
        private String apikey;
    
        // Getters and Setters...
    
        public static class Data {
            private String remoteJid;
            private String instanceId;
            private String name;
            private int unreadMessages;
            public String getRemoteJid() {
                return remoteJid;
            }
            public String getInstanceId() {
                return instanceId;
            }
            public String getName() {
                return name;
            }
            public int getUnreadMessages() {
                return unreadMessages;
            }
    
            // Getters and Setters...
        }

        public String getEvent() {
            return event;
        }

        public String getInstance() {
            return instance;
        }

        public List<Data> getData() {
            return data;
        }

        public String getDestination() {
            return destination;
        }

        public String getDateTime() {
            return dateTime;
        }

        public String getSender() {
            return sender;
        }

        public String getServerUrl() {
            return serverUrl;
        }

        public String getApikey() {
            return apikey;
        }

        @Override
        public String toString() {
            return "Payload [event=" + event + ", instance=" + instance + ", data=" + data + ", destination="
                    + destination + ", dateTime=" + dateTime + ", sender=" + sender + ", serverUrl=" + serverUrl
                    + ", apikey=" + apikey + "]";
        }
}

