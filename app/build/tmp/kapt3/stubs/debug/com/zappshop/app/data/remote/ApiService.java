package com.zappshop.app.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0007J*\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00050\u00040\u00032\b\b\u0003\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010Jv\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00120\u00032\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00172\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\b\u0003\u0010\u001a\u001a\u00020\u000b2\b\b\u0003\u0010\u001b\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ$\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00040\u00032\b\b\u0001\u0010\u001f\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J.\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00120\u00032\b\b\u0003\u0010\u001a\u001a\u00020\u000b2\b\b\u0003\u0010\u001b\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010!\u00a8\u0006\""}, d2 = {"Lcom/zappshop/app/data/remote/ApiService;", "", "getCategories", "Lretrofit2/Response;", "Lcom/zappshop/app/data/model/ApiResponse;", "", "Lcom/zappshop/app/data/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFeaturedProducts", "Lcom/zappshop/app/data/model/Product;", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProducts", "Lcom/zappshop/app/data/model/PaginatedResponse;", "search", "category", "storeId", "minPrice", "", "maxPrice", "sortBy", "page", "perPage", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStoreBySlug", "Lcom/zappshop/app/data/model/Store;", "slug", "getStores", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.GET(value = "api/zappshop/products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProducts(@retrofit2.http.Query(value = "search")
    @org.jetbrains.annotations.Nullable()
    java.lang.String search, @retrofit2.http.Query(value = "category")
    @org.jetbrains.annotations.Nullable()
    java.lang.String category, @retrofit2.http.Query(value = "store_id")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer storeId, @retrofit2.http.Query(value = "min_price")
    @org.jetbrains.annotations.Nullable()
    java.lang.Double minPrice, @retrofit2.http.Query(value = "max_price")
    @org.jetbrains.annotations.Nullable()
    java.lang.Double maxPrice, @retrofit2.http.Query(value = "sort_by")
    @org.jetbrains.annotations.Nullable()
    java.lang.String sortBy, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "per_page")
    int perPage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.PaginatedResponse<com.zappshop.app.data.model.Product>>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/product/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProductById(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.ApiResponse<com.zappshop.app.data.model.Product>>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/featured")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFeaturedProducts(@retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.ApiResponse<java.util.List<com.zappshop.app.data.model.Product>>>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/stores")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStores(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "per_page")
    int perPage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.PaginatedResponse<com.zappshop.app.data.model.Store>>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/store/{slug}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStoreBySlug(@retrofit2.http.Path(value = "slug")
    @org.jetbrains.annotations.NotNull()
    java.lang.String slug, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.ApiResponse<com.zappshop.app.data.model.Store>>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.ApiResponse<java.util.List<com.zappshop.app.data.model.Category>>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}