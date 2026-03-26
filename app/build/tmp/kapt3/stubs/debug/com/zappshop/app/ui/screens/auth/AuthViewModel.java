package com.zappshop.app.ui.screens.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\nJ\u0006\u0010\u0016\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lcom/zappshop/app/ui/screens/auth/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/zappshop/app/data/repository/AuthRepository;", "(Lcom/zappshop/app/data/repository/AuthRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/zappshop/app/data/model/AuthUiState;", "token", "Lkotlinx/coroutines/flow/StateFlow;", "", "getToken", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "login", "", "onEmailChange", "v", "onFullNameChange", "onPasswordChange", "onPhoneChange", "register", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.zappshop.app.data.repository.AuthRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.zappshop.app.data.model.AuthUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.zappshop.app.data.model.AuthUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> token = null;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.zappshop.app.data.repository.AuthRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.zappshop.app.data.model.AuthUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getToken() {
        return null;
    }
    
    public final void onEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onFullNameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onPhoneChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void login() {
    }
    
    public final void register() {
    }
}