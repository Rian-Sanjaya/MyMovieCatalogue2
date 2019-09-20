package com.lonecode.picodiploma.mymoviecatalogue2;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ACTIONBAR_TITLE = "extra_actionbar_title";
    public static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE_DETAIL);

        ImageView imgDetailMovie = findViewById(R.id.img_detail_movie);
        imgDetailMovie.setImageResource(movie.getPhoto());

        TextView tvDetailMovieName = findViewById(R.id.tv_detail_movie_name);
        tvDetailMovieName.setText(movie.getName());

        TextView tvDetailMovieDescription = findViewById(R.id.tv_detail_movie_description);
        tvDetailMovieDescription.setText(movie.getDescription());

        TextView tvDetailUserScore = findViewById(R.id.tv_detail_userscore);
        tvDetailUserScore.setText(movie.getUserScore());

        TextView tvDetailRuntime = findViewById(R.id.tv_detail_runtime);
        tvDetailRuntime.setText(movie.getRuntime());

        TextView tvDetailGenres = findViewById(R.id.tv_detail_genres);
        tvDetailGenres.setText(movie.getGenres());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_ACTIONBAR_TITLE));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
