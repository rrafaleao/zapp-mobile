package com.zappshop.app.ui.screens.cart;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/zappshop/app/ui/screens/cart/CartViewModel;", "Landroidx/lifecycle/ViewModel;", "cartRepository", "Lcom/zappshop/app/data/repository/CartRepository;", "(Lcom/zappshop/app/data/repository/CartRepository;)V", "items", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/zappshop/app/data/model/CartItem;", "getItems", "()Lkotlinx/coroutines/flow/StateFlow;", "clearCart", "", "getTotal", "", "removeItem", "productId", "", "updateQuantity", "quantity", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CartViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.zappshop.app.data.repository.CartRepository cartRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.zappshop.app.data.model.CartItem>> items = null;
    
    @javax.inject.Inject()
    public CartViewModel(@org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.repository.CartRepository cartRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.zappshop.app.data.model.CartItem>> getItems() {
        return null;
    }
    
    public final double getTotal() {
        return 0.0;
    }
    
    public final void removeItem(int productId) {
    }
    
    public final void updateQuantity(int productId, int quantity) {
    }
    
    public final void clearCart() {
    }
}