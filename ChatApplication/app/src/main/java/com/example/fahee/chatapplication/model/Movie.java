package com.example.fahee.chatapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fahee on 1/15/2018.
 */

public class Movie {

    @SerializedName("poster path")
    private String posterpath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String orginialTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;

    public Movie(String posterpath,boolean adult,String overview,String releaseDate,List<Integer>genreIds,Integer id,
                 String originalTitle,String originalLanguage,String title,String backdropPath,Double popularity,Integer voteCount,
                 Boolean video,Double voteAverage){

        this.posterpath = posterpath;
        this.adult=adult;
        this.overview=overview;
        this.releaseDate=releaseDate;
        this.genreIds=genreIds;
        this.id=id;
        this.orginialTitle=originalTitle;
        this.originalLanguage=originalLanguage;
        this.title=title;
        this.backdropPath=backdropPath;
        this.popularity=popularity;
        this.video=video;
        this.voteAverage=voteAverage;
        this.voteCount=voteCount;
    }
    String baseImageUrl = "https://image.tmdb.org/t/p/w500";
    public String getPosterpath(){
        return "https://image.tmdb.org/t/p/w500" + posterpath;
    }
    public void setPosterpath(String posterpath){
        this.posterpath = posterpath;
    }
    public boolean isAdult(){
        return adult;
    }
    public void setAdult(boolean adult){
        this.adult=adult;

    }
    public String getOverview(){
        return overview;
    }
    public String setOverview(String overview) {

        return overview;

    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrginialTitle() {
        return orginialTitle;
    }

    public void setOrginialTitle(String orginialTitle) {
        this.orginialTitle = orginialTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }
}
