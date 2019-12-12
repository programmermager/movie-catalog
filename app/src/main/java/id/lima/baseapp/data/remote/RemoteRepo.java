package id.lima.baseapp.data.remote;

import java.util.Map;

import id.lima.baseapp.data.model.Tags;
import id.lima.baseapp.data.model.UserInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by muhwid on 27/10/18.
 */

public interface RemoteRepo {

    @POST("?login")
    Call<UserInfo> requestLogin(@Body Map<String, Object> params);

    @POST("?recentTags")
    Call<Tags> getTags(@Body Map<String, Object> params);

}
