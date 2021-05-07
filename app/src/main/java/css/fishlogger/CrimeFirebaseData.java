package css.fishlogger;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CrimeFirebaseData {

    DatabaseReference myCrimeDbRef;
    public static final String CrimeDataTag = "CRIME DATA";
    List<Crime> crimeList;

    public DatabaseReference open()  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myCrimeDbRef = database.getReference(CrimeDataTag);
        crimeList =  new ArrayList();
        return myCrimeDbRef;
    }

    public void close() {
    }

    public Crime createCrime(String date, String time, String location,  String description) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myCrimeDbRef.child(CrimeDataTag).push().getKey();
//        String key = "REPLACE THIS WITH KEY FROM DATABASE";
        // ---- set up the fish object
        Crime newCrime = new Crime(key, date, time, location, description);
        // ---- write the vote to Firebase
        myCrimeDbRef.child(key).setValue(newCrime);
        return newCrime;
    }

   /* public Crime createCrime(String date, String time, String location,  String description, String locationLatitude, String locationLongitude) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myCrimeDbRef.child(CrimeDataTag).push().getKey();
//        String key = "REPLACE THIS WITH KEY FROM DATABASE";
        // ---- set up the fish object
        Crime newCrime = new Crime(key, date, time, location, description, locationLatitude,locationLongitude);
        // ---- write the vote to Firebase
        myCrimeDbRef.child(key).setValue(newCrime);
        return newCrime;
    }
    
    */



    public void deleteCrime(Crime crime) {
        String key = crime.getKey();
        myCrimeDbRef.child(key).removeValue();
    }


    public List<Crime> updateCrimeList(DataSnapshot dataSnapshot) {
        crimeList.clear();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Crime crime = data.getValue(Crime.class);
            crimeList.add(crime);
        }
        return crimeList;
    }

    public List<Crime> getAllCrime() {
        return crimeList;
    }

    public Crime getCrime(Integer position) {
        return crimeList.get(position);
    }

    public Integer getNumberOfCrimes() {
        return crimeList.size();
    }

}


