package com.zappshop.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0004H\u00c6\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\nH\u00c6\u0003JC\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\nH\u00d6\u0001R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001e"}, d2 = {"Lcom/zappshop/app/data/model/PaginatedResponse;", "T", "", "success", "", "data", "", "pagination", "Lcom/zappshop/app/data/model/Pagination;", "error", "", "(ZLjava/util/List;Lcom/zappshop/app/data/model/Pagination;Ljava/lang/String;)V", "getData", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "getPagination", "()Lcom/zappshop/app/data/model/Pagination;", "getSuccess", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class PaginatedResponse<T extends java.lang.Object> {
    private final boolean success = false;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<T> data = null;
    @org.jetbrains.annotations.Nullable()
    private final com.zappshop.app.data.model.Pagination pagination = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<T> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.zappshop.app.data.model.Pagination component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.zappshop.app.data.model.PaginatedResponse<T> copy(boolean success, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends T> data, @org.jetbrains.annotations.Nullable()
    com.zappshop.app.data.model.Pagination pagination, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    public PaginatedResponse(boolean success, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends T> data, @org.jetbrains.annotations.Nullable()
    com.zappshop.app.data.model.Pagination pagination, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    public final boolean getSuccess() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<T> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.zappshop.app.data.model.Pagination getPagination() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
}