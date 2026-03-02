suspend fun login(email: String, password: String): Result<AuthResponse> {
    // Mock: qualquer email/senha funciona
    val fakeUser = User(1, "Usuário Teste", email)
    val fakeResponse = AuthResponse("fake-token-123", fakeUser)
    session.saveSession(fakeResponse.token, fakeUser.name, fakeUser.email)
    return Result.success(fakeResponse)
}

suspend fun register(name: String, email: String, password: String): Result<AuthResponse> {
    val fakeUser = User(1, name, email)
    val fakeResponse = AuthResponse("fake-token-123", fakeUser)
    session.saveSession(fakeResponse.token, fakeUser.name, fakeUser.email)
    return Result.success(fakeResponse)
}