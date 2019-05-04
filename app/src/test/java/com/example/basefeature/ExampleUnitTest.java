package com.example.basefeature;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void restTest(){
    /*    RestAPIService.getInstance()
                .getJSONApi()
                .getPostWithID(1)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                        Post post = response.body();

                        System.out.println(post.getId() + "\n");
                        System.out.println( post.getUserId() + "\n");
                        System.out.println(post.getTitle() + "\n");
                        System.out.println(post.getBody() + "\n");
                        Log.d("SOMEWRONG","GO");
                        assertEquals(4,2+2);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {

                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });

            */
    try {
      /*  Response<Post> postResponse = RestAPIService.getInstance()
                .getJSONApi()
                .getPostWithID(1).execute();*/
        Response<Person> response = RestAPIService.getInstance()
                .getJSONApi()
                .getAuthor().execute();
        assertEquals("Igor", response.body().getName());
    }
    catch (IOException e){
        e.printStackTrace();
    }

    }
    @Test
    public void registerTest (){
        Person person = new Person();
        person.setName("Igor");
        person.setSurname(".");
        person.setUsername("igorsan98");
        person.setPassword("qwerty123");
        person.setEmail(".");
        person.setPhone("1234");
        try {
            Response<Person> response = RestAPIService.getInstance()
                    .getJSONApi()
                    .registration(person).execute();
            assertEquals(person.getName(),response.body().getName());
            assertEquals(person.getPassword(),response.body().getPassword());

        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    @Test
    public void auth_good(){
        Person person = new Person();
        person.setUsername("igorsan98");
        person.setPassword("1234");
        try {
            Response<ResponeStats> response = RestAPIService.getInstance()
                    .getJSONApi()
                    .auth(person).execute();
            assertEquals( true,response.body().isGranted());
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    @Test
    public void auth_bad(){
        Person person = new Person();
        person.setUsername("igorsan98");
        person.setPassword("12*4");
        try {
            Response<ResponeStats> response = RestAPIService.getInstance()
                    .getJSONApi()
                    .auth(person).execute();
            assertEquals( false,response.body().isGranted());

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void coord(){
        IssueInfo issueInfo = new IssueInfo();
        issueInfo.setCoord("40.5,30.4");
        LatLng comp = issueInfo.getCoord();
        LatLng latLng = new LatLng(40.5,30.4);
        assertEquals(latLng.latitude,comp.latitude,latLng.latitude-comp.latitude);



    }
}