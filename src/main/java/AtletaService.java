import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by sergi on 11/02/2017.
 */
public interface AtletaService {

    @GET("/atleta") Call<List<Atleta>> getAllAtletas();

    @GET("/atletas/{id}") Call<Atleta> getAtleta(@Path("id") Long id);

    @POST("/atletas") Call<Atleta> crearAtleta(@Body Atleta atleta);

    @PUT("/atletas") Call<Atleta> updateAtleta(@Body Atleta atleta);

    @DELETE("/atletas/{id}") Call<Void> deleteAtleta(@Path("id") Long id);


}
