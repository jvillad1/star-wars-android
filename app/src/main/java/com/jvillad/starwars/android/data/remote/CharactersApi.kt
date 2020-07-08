package com.jvillad.starwars.android.data.remote

import com.jvillad.starwars.android.data.remote.model.CharacterResponse
import com.jvillad.starwars.android.data.remote.model.CharacterSearchResponse
import com.jvillad.starwars.android.data.remote.model.PlanetResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Interface that provides the Star Wars Characters API End-Points.
 *
 * @author juan.villada
 */
interface CharactersApi {

    @GET("people/")
    suspend fun searchCharacters(
        @Query("search") name: String
    ): CharacterSearchResponse

    @GET
    suspend fun getPlanet(
        @Url url: String
    ): PlanetResponse

    @GET("people/{id}/")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterResponse
}
