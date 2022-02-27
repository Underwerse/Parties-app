package fi.example.parties.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =
	"https://avoindata.eduskunta.fi/"

private val moshi = Moshi.Builder()
	.add(KotlinJsonAdapterFactory())
	.build()

private val retrofit = Retrofit.Builder()
	.addConverterFactory(MoshiConverterFactory.create(moshi))
	.baseUrl(BASE_URL)
	.build()

interface ApiServiceImages {
	/**
	 * Returns a Coroutine [List] of [Member] which can be fetched in a Coroutine scope.
	 * The @GET annotation indicates that the "mps.json" endpoint will be requested with the GET
	 * HTTP method
	 */
	@GET("mps.json")
	suspend fun getMembers() : List<Member>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ImagesApi {
	val RETROFIT_SERVICE_IMAGES : ApiServiceImages by lazy { retrofit.create(ApiServiceImages::class.java) }
}