package css.fishlogger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button buttonAddCrime;

    RecyclerView recycleViewFish;
    RecycleViewAdapter recycleViewAdapter;

    CrimeFirebaseData crimeDataSource;
    DatabaseReference myCrimeDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("CIS3334", "Starting onCreate");        // debugging log

        setupButtonAddFish();
        setupFirebaseDataChange();
        setupRecycleView();

    }

    private void setupFirebaseDataChange() {
        crimeDataSource = new CrimeFirebaseData();
        myCrimeDbRef = crimeDataSource.open();
        myCrimeDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CIS3334", "Starting onDataChange()");        // debugging log
                crimeDataSource.updateCrimeList(dataSnapshot);
                recycleViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CIS3334", "onCancelled: ");
            }
        });

    }


    private void setupRecycleView() {
        recycleViewFish = findViewById(R.id.recycleViewCrime);
        recycleViewAdapter = new RecycleViewAdapter(crimeDataSource);
        recycleViewFish.setAdapter(recycleViewAdapter);
        recycleViewFish.setLayoutManager(new LinearLayoutManager(this));
        recycleViewAdapter.notifyDataSetChanged();
    }

    private void setupButtonAddFish() {
        Log.d("CIS3334", "Starting setupButtonAddFish");        // debugging log
        buttonAddCrime = findViewById(R.id.buttonAddCrime);
        buttonAddCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start up the add fish activity with an intent
                Intent addActIntent = new Intent(v.getContext(), AddCrimeActivity.class);
                //finish();
                startActivity(addActIntent);
            }
        });
    }
}