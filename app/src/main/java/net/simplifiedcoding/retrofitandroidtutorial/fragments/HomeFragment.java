package net.simplifiedcoding.retrofitandroidtutorial.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.adapters.PronosAdapter;
import net.simplifiedcoding.retrofitandroidtutorial.api.RetrofitClient;
import net.simplifiedcoding.retrofitandroidtutorial.models.Pronos;
import net.simplifiedcoding.retrofitandroidtutorial.models.PronosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewp;
    private PronosAdapter adapter;
    private List<Pronos> pronosList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pronos_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewp = view.findViewById(R.id.recyclerViewp);
        recyclerViewp.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<PronosResponse> call = RetrofitClient.getInstance().getApi().getPronos();

        call.enqueue(new Callback<PronosResponse>() {
            @Override
            public void onResponse(Call<PronosResponse> call, Response<PronosResponse> response) {

                pronosList = response.body().getPronos();
                adapter = new PronosAdapter(getActivity(), pronosList);
                recyclerViewp.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PronosResponse> call, Throwable t) {

            }
        });

    }
}
