package css.fishlogger;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<CrimeRowViewHolder> {

    CrimeFirebaseData crimeDataSource;

    RecycleViewAdapter (CrimeFirebaseData crimeDataSource) {
        this.crimeDataSource = crimeDataSource;
    }

    @NonNull
    @Override
    public CrimeRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fish_row_layout, parent, false);
        CrimeRowViewHolder holder = new CrimeRowViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull CrimeRowViewHolder holder, int position) {
        Crime crime = crimeDataSource.getCrime(position);
        Log.d("CIS 3334", "RecycleViewAdapter: Display crime of "+crime);
        String description = crime.getDescription();
        holder.textViewDesc.setText(description);
        holder.textViewLocation.setText(crime.getLocation());
        holder.textViewTime.setText(crime.getTime());
        holder.textViewDate.setText(crime.getDate());

        /* if (species.toLowerCase().contains("bass")) {
            holder.imageViewFishIcon.setImageResource(R.drawable.largemouth);
        } else if (species.toLowerCase().contains("walleye")) {
            holder.imageViewFishIcon.setImageResource(R.drawable.walleye);
        } else if (species.toLowerCase().contains("northern")) {
            holder.imageViewFishIcon.setImageResource(R.drawable.northern);
        } else {
            holder.imageViewFishIcon.setImageResource(R.drawable.fish);
        }

         */
    }

    @Override
    public int getItemCount() {
        Log.d("CIS 3334", "RecycleViewAdapter: getItemCount = "+crimeDataSource.getNumberOfCrimes());
        return crimeDataSource.getNumberOfCrimes();
    }
}
