package com.lonecode.picodiploma.mymoviecatalogue2;


import android.content.Intent;
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
    private TypedArray dataPhotoMovie;
    private String[] dataNameMovie;
    private String[] dataDescriptionMovie;
    private String[] dataUserScoreMovie;
    private String[] dataRuntimeMovie;
    private String[] dataGenresMovie;

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
        dataPhotoMovie = getResources().obtainTypedArray(R.array.data_photo_tv);
        dataNameMovie = getResources().getStringArray(R.array.data_name_tv);
        dataDescriptionMovie = getResources().getStringArray(R.array.data_description_tv);
        dataUserScoreMovie = getResources().getStringArray(R.array.data_user_score_tv);
        dataRuntimeMovie = getResources().getStringArray(R.array.data_runtime_tv);
        dataGenresMovie = getResources().getStringArray(R.array.data_genres_tv);
    }

    private ArrayList<Movie> addItem() {
        ArrayList<Movie> tvShowList = new ArrayList<>();

        for (int i = 0; i < dataNameMovie.length; i++) {
            Movie movie = new Movie();

            movie.setPhoto(dataPhotoMovie.getResourceId(i, -1));
            movie.setName(dataNameMovie[i]);
            movie.setDescription(dataDescriptionMovie[i]);
            movie.setUserScore(dataUserScoreMovie[i]);
            movie.setRuntime(dataRuntimeMovie[i]);
            movie.setGenres(dataGenresMovie[i]);

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
//        Toast.makeText(getActivity(), "Tv: " + data.getName(), Toast.LENGTH_SHORT).show();
        Intent movieDetailIntent = new Intent(getContext(), MovieDetailActivity.class);
        movieDetailIntent.putExtra(MovieDetailActivity.EXTRA_ACTIONBAR_TITLE, getString(R.string.tvshow_detail));
        movieDetailIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE_DETAIL, data);
        startActivity(movieDetailIntent);
    }
}
