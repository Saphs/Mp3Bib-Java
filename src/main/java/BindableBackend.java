public interface BindableBackend {
    void pushRequest(String request);
    void bind(BindableFrontend frontend);
    void unbind(BindableFrontend frontend);
}
