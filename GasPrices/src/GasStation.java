public class GasStation {

    private String stationName = "";
    private String cost = "";
    private String address = "";
    private String lastUpdated = "---";
    private String amntReviews = "";
    private String stars = "";
    GasStation(String s, String c, String a, String t, String reviews, String strs){
        stationName = s;
        cost = c;
        address = a;
        lastUpdated = t;
        amntReviews = reviews;
        stars = strs;
    }

    public String getStationName(){
        return stationName;
    }
    public void setStationName(String n){
        stationName = n;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String a){
        address = a;
    }
    public String getCost(){
        return cost;
    }
    public void setCost(String c){
        cost = c;
    }
    public String getLastUpdated(){
        return lastUpdated;
    }
    public void setLastUpdated(String t){
        lastUpdated = t;
    }

    public String getAmntReviews() {
        return amntReviews;
    }

    public void setAmntReviews(String a) {
        amntReviews = a;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String s) {
        stars = s;
    }
}
