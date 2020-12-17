
public class GasStation implements Comparable<GasStation> {

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

    @Override
    public int compareTo(GasStation o) {
        float tC = -1, oC = -1;
        //check if gas station is Canadian and if is, remove the '¢' sign
        if (this.cost.contains("¢")){
            tC = Float.parseFloat(this.cost.substring(0, this.cost.length() - 1));
            if (!o.cost.equals("---")){
                oC = Float.parseFloat(o.cost.substring(0, o.cost.length() - 1));
            }
        }
        else if (o.cost.contains("¢")){
            oC = Float.parseFloat(o.cost.substring(0, o.cost.length() - 1));
            if (!this.cost.equals("---")){
                tC = Float.parseFloat(this.cost.substring(0, this.cost.length() - 1));
            }
        }
        //check if there is a cost since we confirmed the station is not from Canada
        else{
            //if there is a cost, it is american, must remove dollar sign
            if (!this.cost.equals("---")){
                tC = Float.parseFloat(this.cost.substring(1, this.cost.length()));
            }
            if (!o.cost.equals("---")){
                oC = Float.parseFloat(o.cost.substring(1, o.cost.length()));
            }
        }

        return  (int)(oC * 100) - (int)(tC * 100);
    }
}
