package css.fishlogger;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CrimeRowViewHolder extends RecyclerView.ViewHolder{
    TextView textViewLocation;
    TextView textViewDesc;
    TextView textViewTime;
    TextView textViewDate;
    ImageView imageViewFishIcon;

    public CrimeRowViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewLocation = itemView.findViewById(R.id.textViewLocation);
        textViewDesc = itemView.findViewById(R.id.textViewDesc);
        textViewTime = itemView.findViewById(R.id.textViewTime);
        textViewDate = itemView.findViewById(R.id.textViewDate);
        imageViewFishIcon = itemView.findViewById(R.id.imageViewCrime);
    }
}
