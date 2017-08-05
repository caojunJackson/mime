package cn.everelegance.ugank.network.api;

import cn.everelegance.ugank.entity.CategroyResult;
import cn.everelegance.ugank.entity.SearchResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/4.
 */

public interface GankApi {

    @GET("data/{category}/{number}/{page}")
    Observable<CategroyResult> getCategoryDate(@Path("category") String category , @Path("number") int number , @Path("page") int page);

    @GET("random/data/福利/{number}")
    Observable<CategroyResult> getRandomBeauties(@Path("number") int number);

    @GET("search/query/{key}/category/all/count/{count}/page/{page}")
    Observable<SearchResult> getSearchResult(@Path("key") String key, @Path("count") int count, @Path("page") int page);

}
