package com.zappshop.app.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ*\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\b\u0003\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J(\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0013\u001a\u00020\n2\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016J(\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0013\u001a\u00020\n2\b\b\u0001\u0010\u0014\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/zappshop/app/data/remote/ApiService;", "", "getCategories", "Lretrofit2/Response;", "", "Lcom/zappshop/app/data/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "Lcom/zappshop/app/data/model/AuthResponse;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProducts", "Lcom/zappshop/app/data/model/ZappShopResponse;", "search", "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "slug", "request", "Lcom/zappshop/app/data/model/LoginRequest;", "(Ljava/lang/String;Lcom/zappshop/app/data/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/zappshop/app/data/model/RegisterRequest;", "(Ljava/lang/String;Lcom/zappshop/app/data/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "{slug}/auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Path(value = "slug")
    @org.jetbrains.annotations.NotNull()
    java.lang.String slug, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.model.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.AuthResponse>> $completion);
    
    @retrofit2.http.POST(value = "{slug}/auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Path(value = "slug")
    @org.jetbrains.annotations.NotNull()
    java.lang.String slug, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.model.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.AuthResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProducts(@retrofit2.http.Query(value = "search")
    @org.jetbrains.annotations.Nullable()
    java.lang.String search, @retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.ZappShopResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/product/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProductById(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.zappshop.app.data.model.AuthResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/zappshop/categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.zappshop.app.data.model.Category>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}