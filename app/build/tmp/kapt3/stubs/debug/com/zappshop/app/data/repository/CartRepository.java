package com.zappshop.app.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/zappshop/app/data/repository/CartRepository;", "", "()V", "_items", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/zappshop/app/data/model/CartItem;", "items", "Lkotlinx/coroutines/flow/StateFlow;", "getItems", "()Lkotlinx/coroutines/flow/StateFlow;", "addItem", "", "product", "Lcom/zappshop/app/data/model/Product;", "clearCart", "getTotal", "", "removeItem", "productId", "", "updateQuantity", "quantity", "", "app_debug"})
public final class CartRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.zappshop.app.data.model.CartItem>> _items = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.zappshop.app.data.model.CartItem>> items = null;
    
    @javax.inject.Inject()
    public CartRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.zappshop.app.data.model.CartItem>> getItems() {
        return null;
    }
    
    public final void addItem(@org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.model.Product product) {
    }
    
    public final void removeItem(@org.jetbrains.annotations.NotNull()
    java.lang.String productId) {
    }
    
    public final void updateQuantity(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, int quantity) {
    }
    
    public final double getTotal() {
        return 0.0;
    }
    
    public final void clearCart() {
    }
}