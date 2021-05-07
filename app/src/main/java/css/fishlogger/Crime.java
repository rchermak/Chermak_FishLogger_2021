package css.fishlogger;

public class Crime  {
    private String key;
    private String date;
    private String time;
    private String location;
    private String description;
    //private String locationCaughtLatitude;
    //private String locationCaughtLongitude;

   // public Fish() {
   // }

    public Crime(String key, String date, String time, String location,  String description) {
        this.key = key;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
        //this.locationCaughtLatitude = locationCaughtLatitude;
        //this.locationCaughtLongitude = locationCaughtLongitude;
    }

  /*
    public Fish(String key, String species, String weightInOz, String dateCaught) {
        this.key = key;
        this.species = species;
        this.weightInOz = weightInOz;
        this.dateCaught = dateCaught;
        this.locationCaughtLatitude = locationCaughtLatitude;
        this.locationCaughtLongitude = locationCaughtLongitude;
    }

   */

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location ;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* public String getLocationCaughtLatitude() {
        return locationCaughtLatitude;
    }
    */
   /* public void setLocationCaughtLatitude(String locationCaughtLatitude) {
        this.locationCaughtLatitude = locationCaughtLatitude;
    }
    */

    /* public String getLocationCaughtLongitude() {
        return locationCaughtLongitude;
    }
    */

    /*
    public void setLocationCaughtLongitude(String locationCaughtLongitude) {
        this.locationCaughtLongitude = locationCaughtLongitude;
    }

     */

    @Override
    public String toString() {
        return "Crime{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
