package net.simplifiedcoding.retrofitandroidtutorial.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.simplifiedcoding.retrofitandroidtutorial.RecyclerViewClickListener;
import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.models.Pronos;
import net.simplifiedcoding.retrofitandroidtutorial.models.PronosResponse;

import java.util.List;

import retrofit2.Callback;

public class PronosAdapter extends RecyclerView.Adapter<PronosAdapter.PronosViewHolder>{

    private Context mCtx;
    private List<Pronos> pronosList;
    private RecyclerViewClickListener mListener;

    public PronosAdapter(Context mCtx, List<Pronos> pronosList, RecyclerViewClickListener listener) {
        this.mCtx = mCtx;
        this.pronosList = pronosList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PronosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_pronos, parent, false);
        return new PronosViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PronosAdapter.PronosViewHolder holder, int position) {
        Pronos pronos = pronosList.get(position);

        holder.textViewEquipe1.setText(pronos.getEquipe1());
        holder.textViewEquipe2.setText(pronos.getEquipe2());
        holder.textViewCote1.setText(String.valueOf(pronos.getCote1()));
        holder.textViewCote2.setText(String.valueOf(pronos.getCote2()));
        holder.textViewCoteNull.setText(String.valueOf(pronos.getMatchNull()));
    }

    @Override
    public int getItemCount() {
        return pronosList.size();
    }

    class PronosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewEquipe1, textViewEquipe2, textViewCote1,textViewCote2,textViewCoteNull;
        private RecyclerViewClickListener mListener;

        public PronosViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            textViewEquipe1 = itemView.findViewById(R.id.textViewEquipe1);
            textViewEquipe2 = itemView.findViewById(R.id.textViewEquipe2);
            textViewCote1 = itemView.findViewById(R.id.textViewCote1);
            textViewCote2 = itemView.findViewById(R.id.textViewCote2);
            textViewCoteNull = itemView.findViewById(R.id.textViewCoteNull);
            mListener = listener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}


