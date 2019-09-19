package com.lonecode.picodiploma.mymoviecatalogue2;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private View rootview;
    private RecyclerView rvTvShow;
    private ArrayList<Movie> list = new ArrayList<>();
    private String[] dataNameMovie;
    private String[] dataDescriptionMovie;
    private TypedArray dataPhotoMovie;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_tvshow, container, false);
        rvTvShow = rootview.findViewById(R.id.rv_tvshow);
        rvTvShow.setHasFixedSize(true);

        prepareData();
        list.addAll(addItem());
        showRecyclerList();

        return rootview;

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    private void prepareData() {
        dataNameMovie = getResources().getStringArray(R.array.data_name_movie);
        dataDescriptionMovie = getResources().getStringArray(R.array.data_description_movie);
        dataPhotoMovie = getResources().obtainTypedArray(R.array.data_photo_tv);
    }

    private ArrayList<Movie> addItem() {
        ArrayList<Movie> tvShowList = new ArrayList<>();

        for (int i = 0; i < dataNameMovie.length; i++) {
            Movie movie = new Movie();

            movie.setName(dataNameMovie[i]);
            movie.setDescription(dataDescriptionMovie[i]);
            movie.setPhoto(dataPhotoMovie.getResourceId(i, -1));

            tvShowList.add(movie);
        }

        return tvShowList;
    }

    private void showRecyclerList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
        rvTvShow.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedTv(data);
            }
        });
    }

    private void showSelectedTv(Movie data) {
        Toast.makeText(getActivity(), "Tv: " + data.getName(), Toast.LENGTH_SHORT).show();
    }
}
