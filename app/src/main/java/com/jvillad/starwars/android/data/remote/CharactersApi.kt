package com.jvillad.starwars.android.data.remote

import com.jvillad.starwars.android.data.remote.model.CharacterResponse
import com.jvillad.starwars.android.data.remote.model.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface that provides the Star Wars Characters API End-Points.
 *
 * @author juan.villada
 */
interface CharactersApi {

    @GET("people/")
    suspend fun searchCharacters(
        @Query("name") name: String
    ): CharacterSearchResponse

    @GET("people/{id}/")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): CharacterResponse
}

