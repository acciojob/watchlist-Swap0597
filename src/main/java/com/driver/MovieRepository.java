package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String, Movie> movieList = new HashMap<>();
    Map<String , Director> directorList = new HashMap<>();
    Map<Movie, Director> movieDirector = new HashMap<>();

    public String addMovie(Movie movie) {

        String name = movie.getName();
        movieList.put(name, movie);
        return  "Movie added successfully";
    }

    public String addDirector(Director director) {

        String name = director.getName();
        directorList.put(name, director);
        return "Director added Successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {

        Movie movie = movieList.get(movieName);
        Director director = directorList.get(directorName);
        movieDirector.put(movie,director);

        return "Movie and Director Successfully added";
    }



    public Movie getMovieByName(String name) {

        Movie movie = movieList.get(name);
        return movie;
    }

    public Director getDirectorByName(String name) {
        Director director = directorList.get(name);
        return director;
    }


    public List<String> getMoviesByDirectorName(String director) {
        List<String> list = new ArrayList<>();

        for(Movie m : movieDirector.keySet()){
            if((movieDirector.get(m).getName()).equals(director)){
                list.add(m.getName());
            }
        }
        return list;
    }

    public List<String> findAllMovies() {
        List<String> list = new ArrayList<>();

        for(String s : movieList.keySet()){
            list.add(s);
        }
        return list;
    }

    public String deleteDirectorByName(String name) {

        for(Movie m : movieDirector.keySet()){
            if((movieDirector.get(m).getName()).equals(name)){
                movieList.remove(m.getName());
                //removeMovieFromMovieList(m.getName());
                movieDirector.remove(m);
            }
        }
        directorList.remove(name);
        return "Director and Movie removed Successfully";
    }

    public String deleteAllDirectors() {
        for(String dName : directorList.keySet())
        {
            for(Movie m : movieDirector.keySet())
            {
                if((movieDirector.get(m).getName()).equals(dName)){
                    //removeMovieFromMovieList(m.getName());
                    movieList.remove(m.getName());
                    movieDirector.remove(m);
                }
            }
            directorList.remove(dName);
        }

        return "All Record Deleted";
    }
}
