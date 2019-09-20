package com.lonecode.picodiploma.mymoviecatalogue2;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
public class MoviesFragment extends Fragment {
    private View rootview;
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();
    private TypedArray dataPhotoMovie;
    private String[] dataNameMovie;
    private String[] dataDescriptionMovie;
    private String[] dataUserScoreMovie;
    private String[] dataRuntimeMovie;
    private String[] dataGenresMovie;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_movies, container, false);
        rvMovies = rootview.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        prepareData();
        list.addAll(addItem());
        showRecyclerList();

        return rootview;
    }

    private void prepareData() {
        dataPhotoMovie = getResources().obtainTypedArray(R.array.data_photo_movie);
        dataNameMovie = getResources().getStringArray(R.array.data_name_movie);
        dataDescriptionMovie = getResources().getStringArray(R.array.data_description_movie);
        dataUserScoreMovie = getResources().getStringArray(R.array.data_user_score_movie);
        dataRuntimeMovie = getResources().getStringArray(R.array.data_runtime_movie);
        dataGenresMovie = getResources().getStringArray(R.array.data_genres_movie);
    }

    private ArrayList<Movie> addItem() {
        ArrayList<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < dataNameMovie.length; i++) {
            Movie movie = new Movie();

            movie.setPhoto(dataPhotoMovie.getResourceId(i, -1));
            movie.setName(dataNameMovie[i]);
            movie.setDescription(dataDescriptionMovie[i]);
            movie.setUserScore(dataUserScoreMovie[i]);
            movie.setRuntime(dataRuntimeMovie[i]);
            movie.setGenres(dataGenresMovie[i]);

            movieList.add(movie);
        }

        return movieList;
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
        rvMovies.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });
    }

    private void showSelectedMovie(Movie movie) {
//        Toast.makeText(getActivity(), "Movie: " + movie.getName() + "\nUser Score: " + movie.getUserScore(), Toast.LENGTH_SHORT).show();
        Intent movieDetailIntent = new Intent(getContext(), MovieDetailActivity.class);
        movieDetailIntent.putExtra(MovieDetailActivity.EXTRA_ACTIONBAR_TITLE, getString(R.string.movie_detail));
        movieDetailIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE_DETAIL, movie);
        startActivity(movieDetailIntent);
    }
}
