package com.example.basefeature;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);

    @GET("/posts")
    public Call<List<Post>> getAllPosts();

    @GET("/posts")
    public Call<List<Post>> getPostOfUser(@Query("userId") int id);

    @POST("/posts")
    public Call<Post> postData(@Body Post data);

    @GET("/watcher/api/author")
    public Call<Person> getAuthor();
    //регистрация
    @POST("/watcher/api/register")
    public Call<Person>registration(@Body Person person);
    //авторизация
    @POST("/watcher/api/auth")
    public Call<ResponeStats>auth(@Body Person person);
    //отсылаю заявку
    @Multipart
    @POST("/watcher/api/send_issue")
    public Call<ResponeStats>sendIssue(@Part IssueInfo issue,@Part MultipartBody.Part photo);

   //возращает заявку

    @GET("/watcher/api/issue/{id}")
    public Call<IssueInfo>getIssue(int id);
    //возращает пикчу
    @GET("/watcher/api/issue/{id}/image")
    public Call<ResponseBody>getIssueImage(int id);
}
