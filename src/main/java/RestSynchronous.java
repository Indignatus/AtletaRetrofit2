import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by sergi on 11/02/2017.
 */
public class RestSynchronous {

    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {


        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AtletaService atletaService = retrofit.create(AtletaService.class);


        Response<List<Atleta>> responseAllAtletas = atletaService.getAllAtletas().execute();

        if (responseAllAtletas.isSuccessful()) {
            List<Atleta> listaAtletas = responseAllAtletas.body();

            System.out.println("Listado : " + responseAllAtletas.code() + "todos los atletas " + listaAtletas);
        } else {
            System.out.println(responseAllAtletas.code() + "Message error: " + responseAllAtletas.errorBody());
        }
        Atleta atleta1 = new Atleta();
        atleta1.setName("Sergi");
        atleta1.setApellido("Tirado");
        atleta1.setNacionalidad("Español");
        atleta1.setFechaNacimiento(LocalDate.of(1996, 02, 04));


        Response<Atleta> crearAtleta = atletaService.crearAtleta(atleta1).execute();


        // final etapa de crer Atleta

// Etapa actualizar

        if (crearAtleta.isSuccessful()) {

            Atleta actualizarAtleta = crearAtleta.body();

            System.out.println("Antes de update " + crearAtleta.code());


            actualizarAtleta.setName("Indignatus");
            Response<Atleta> responseActualizarAtleta = atletaService.updateAtleta(actualizarAtleta).execute();



            if(responseActualizarAtleta.isSuccessful()){



                System.out.println("Después de actualizar : " + actualizarAtleta);

                // Etapa borrar

                Response<Void> borrarAtlera = atletaService.deleteAtleta(actualizarAtleta.getId()).execute();

                if(borrarAtlera.isSuccessful()){

                    System.out.println("Atleta borrado correctamente");

                }else{

                    System.out.println("Error no se ha podido borrar el atleta");
                }

            }



        }








        }

    }



