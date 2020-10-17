package com.crawler.api.nh.vo;

public class NHRequest {

        private String depCity;
        private String arrCity;
        private String flightDate;
        private String adultNum;
        private String childNum;
        private String infantNum;
        private String cabinOrder;
        private int airLine;
        //直飞 0  中转 2
        private int flyType;
        private String international;
        private String action;
        private String segType;
        private int cache;
        private String preUrl;
        private String isMember;
        public void setDepCity(String depCity) {
            this.depCity = depCity;
        }
        public String getDepCity() {
            return depCity;
        }

        public void setArrCity(String arrCity) {
            this.arrCity = arrCity;
        }
        public String getArrCity() {
            return arrCity;
        }

        public void setFlightDate(String flightDate) {
            this.flightDate = flightDate;
        }
        public String getFlightDate() {
            return flightDate;
        }

        public void setAdultNum(String adultNum) {
            this.adultNum = adultNum;
        }
        public String getAdultNum() {
            return adultNum;
        }

        public void setChildNum(String childNum) {
            this.childNum = childNum;
        }
        public String getChildNum() {
            return childNum;
        }

        public void setInfantNum(String infantNum) {
            this.infantNum = infantNum;
        }
        public String getInfantNum() {
            return infantNum;
        }

        public void setCabinOrder(String cabinOrder) {
            this.cabinOrder = cabinOrder;
        }
        public String getCabinOrder() {
            return cabinOrder;
        }

        public void setAirLine(int airLine) {
            this.airLine = airLine;
        }
        public int getAirLine() {
            return airLine;
        }

        public void setFlyType(int flyType) {
            this.flyType = flyType;
        }
        public int getFlyType() {
            return flyType;
        }

        public void setInternational(String international) {
            this.international = international;
        }
        public String getInternational() {
            return international;
        }

        public void setAction(String action) {
            this.action = action;
        }
        public String getAction() {
            return action;
        }

        public void setSegType(String segType) {
            this.segType = segType;
        }
        public String getSegType() {
            return segType;
        }

        public void setCache(int cache) {
            this.cache = cache;
        }
        public int getCache() {
            return cache;
        }

        public void setPreUrl(String preUrl) {
            this.preUrl = preUrl;
        }
        public String getPreUrl() {
            return preUrl;
        }

        public void setIsMember(String isMember) {
            this.isMember = isMember;
        }
        public String getIsMember() {
            return isMember;
        }

    }
