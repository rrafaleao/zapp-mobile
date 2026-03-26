package com.zappshop.app.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J,\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J<\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001f"}, d2 = {"Lcom/zappshop/app/data/repository/AuthRepository;", "", "()V", "_token", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_userName", "api", "Lcom/zappshop/app/data/remote/ApiService;", "defaultSlug", "token", "Lkotlinx/coroutines/flow/StateFlow;", "getToken", "()Lkotlinx/coroutines/flow/StateFlow;", "userName", "getUserName", "login", "Lkotlin/Result;", "Lcom/zappshop/app/data/model/AuthData;", "email", "password", "login-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "register", "name", "phone", "pass", "register-yxL6bBk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.zappshop.app.data.remote.ApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String defaultSlug = "zappshop";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _token = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> token = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _userName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> userName = null;
    
    @javax.inject.Inject()
    public AuthRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getUserName() {
        return null;
    }
    
    public final void logout() {
    }
}