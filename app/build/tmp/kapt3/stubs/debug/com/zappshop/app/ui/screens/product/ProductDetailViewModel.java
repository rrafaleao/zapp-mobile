package com.zappshop.app.ui.screens.product;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/zappshop/app/ui/screens/product/ProductDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "productRepository", "Lcom/zappshop/app/data/repository/ProductRepository;", "cartRepository", "Lcom/zappshop/app/data/repository/CartRepository;", "(Lcom/zappshop/app/data/repository/ProductRepository;Lcom/zappshop/app/data/repository/CartRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/zappshop/app/ui/screens/product/ProductDetailUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addToCart", "", "loadProduct", "id", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProductDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.zappshop.app.data.repository.ProductRepository productRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.zappshop.app.data.repository.CartRepository cartRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.zappshop.app.ui.screens.product.ProductDetailUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.zappshop.app.ui.screens.product.ProductDetailUiState> uiState = null;
    
    @javax.inject.Inject()
    public ProductDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.repository.ProductRepository productRepository, @org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.repository.CartRepository cartRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.zappshop.app.ui.screens.product.ProductDetailUiState> getUiState() {
        return null;
    }
    
    public final void loadProduct(int id) {
    }
    
    public final void addToCart() {
    }
}