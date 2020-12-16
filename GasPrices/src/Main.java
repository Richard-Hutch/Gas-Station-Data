/*
Name: Richard Hutcheson
Date: 12/15/2020

Program finds the gas stations with the cheapest gas prices
for the user's requested city
*/
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.Scanner; //for user input

public class Main {

    public static void main(String[] args) {

        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Please enter the city you would like to find gas prices for: ");
        String city = scannerObj.nextLine();
        //CONSIDERATION: may need to replace every space with "%20"

        final String WEBSITE_URL = "https://www.gasbuddy.com/home?search=" + city + "&fuel=1&maxAge=0&method=";
        final String FULL_STAR = "color:#f6cc1c";

        try{
            final Document document = Jsoup.connect(WEBSITE_URL).get(); //gets the website's html
            String line = "";
            //holds the indiviual gas stations and their information
            ArrayList<GasStation> stationList = new ArrayList<GasStation>();

            //gets the HTML information for each gas station to break data up appropriately
            for (Element station : document.select(
                    "div.panel__panel___3Q2zW.panel__white___19KTz.colors__bg" +
                            "White___1stjL.panel__bordered___1Xe-S.panel__rounded___2" +
                            "etNE.GenericStationListItem__station___1O4vF.GenericStat" +
                            "ionListItem__clickable___30MZX")){

                String name = station.select("h3.header__header3___1b1oq.header__header___1zI" +
                        "I0.header__midnight___1tdCQ." + "header__snug___lRSNK.GenericStationListItem" +
                        "__stationNameHeader___3qxdy").text();
                //get subName if it exists and then append it to name of the station
                String subName = station.select("div.GenericStationListItem__stationNameSubHeade" +
                        "r___2UsAv.text__greyishBrown___3tBNd").text();
                if (!subName.equals("")){
                    name = name + " " + subName;
                }
                //determine the amnt of stars
                float tempAmntStars= 0;
                for (Element temp : station.select("span.text__left___1iOw3")){
                    //check if the star is filled or half-filled then determine which
                    if (temp.attr("style").equals(FULL_STAR)){
                        //full star
                        //retrieves this by searching for the svg element and then retrieving its "data-icon" attribute
                        if (temp.select("svg").attr("data-icon").equals("star")){
                            tempAmntStars += 1;
                        }
                        //half star
                        else{
                            tempAmntStars += .5;
                        }
                    }
                }
                //convert amount of stars to string
                String amntStars = String.valueOf(tempAmntStars);

                //get the amnt of stars
                String amntReviews = station.select("span.text__left___1iOw3.GenericStationList" +
                        "Item__numberOfReviews___3Ow1k").text();

                //get the address of the station
                String address = station.select("div.GenericStationListItem__address___1VFQ3").text();

                //get the cost of gas at the station
                String cost = station.select("span.text__left___1iOw3.GenericStationListItem__price___3GpKP").text();

                //get the last update time at the station
                String updateTime = station.select("span.ReportedBy__postedTime___J5H9Z").text();

                //add Gas Station to list
                stationList.add(new GasStation(name, cost, address, updateTime, amntReviews, amntStars));

            }
            //display the gas stations and their information
            for (int i = 0; i < stationList.size(); ++i){
                System.out.println("Name:           " + stationList.get(i).getStationName());
                System.out.println("Address:        " + stationList.get(i).getAddress());
                System.out.print("Rating:         " + stationList.get(i).getStars() + "/5 stars");
                System.out.println(" with " + stationList.get(i).getAmntReviews() + " reviews");
                //the gas station did not have a price, therefore no last updated time either
                if (stationList.get(i).getCost().equals("---")){
                    System.out.println("Cost:           price unknown");
                }else{
                    System.out.print("Cost:           " + stationList.get(i).getCost());
                    System.out.println(" (last updated " + stationList.get(i).getLastUpdated() + ")");
                }
                System.out.println();
            }

        }
        //there was an error while processing the website or the contents of the website
        catch(Exception exc) {
            System.out.println("ERROR while processing URL");
            exc.printStackTrace();
        }
    }
}
