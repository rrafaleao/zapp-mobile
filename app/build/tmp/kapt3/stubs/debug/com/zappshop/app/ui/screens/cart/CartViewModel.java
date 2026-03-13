package com.zappshop.app.ui.screens.cart;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/zappshop/app/ui/screens/cart/CartViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/zappshop/app/data/repository/CartRepository;", "(Lcom/zappshop/app/data/repository/CartRepository;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/zappshop/app/data/model/CartItem;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkout", "", "getTotalPrice", "", "removeItem", "productId", "", "updateQuantity", "quantity", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CartViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.zappshop.app.data.repository.CartRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.zappshop.app.data.model.CartItem>> uiState = null;
    
    @javax.inject.Inject()
    public CartViewModel(@org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.repository.CartRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.zappshop.app.data.model.CartItem>> getUiState() {
        return null;
    }
    
    public final double getTotalPrice() {
        return 0.0;
    }
    
    public final void updateQuantity(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, int quantity) {
    }
    
    public final void removeItem(@org.jetbrains.annotations.NotNull()
    java.lang.String productId) {
    }
    
    public final void checkout() {
    }
}