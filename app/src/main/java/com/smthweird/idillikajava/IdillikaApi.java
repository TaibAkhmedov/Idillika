package com.smthweird.idillikajava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IdillikaApi {

    @GET("catalogList.php")
    Call<List<CatalogItem>> getCatalogList(
            @Query("section") Integer section,
            @Query("session_id") String id
    );
}
